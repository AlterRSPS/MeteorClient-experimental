/*
 * Copyright (c) 2019, Trevor <https://github.com/Trevor159>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.questhelper.questhelpers;

import com.google.inject.Binder;
import com.google.inject.CreationException;
import com.google.inject.Module;
import net.runelite.client.plugins.questhelper.ExternalQuestResources;
import net.runelite.client.plugins.questhelper.QuestBank;
import net.runelite.client.plugins.questhelper.QuestHelperConfig;
import net.runelite.client.plugins.questhelper.QuestHelperPlugin;
import net.runelite.client.plugins.questhelper.QuestHelperQuest;
import net.runelite.client.plugins.questhelper.panel.PanelDetails;
import net.runelite.client.plugins.questhelper.requirements.item.ItemRequirement;
import net.runelite.client.plugins.questhelper.requirements.Requirement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import net.runelite.client.plugins.questhelper.rewards.ExperienceReward;
import net.runelite.client.plugins.questhelper.rewards.ItemReward;
import net.runelite.client.plugins.questhelper.rewards.QuestPointReward;
import net.runelite.client.plugins.questhelper.rewards.UnlockReward;
import lombok.Getter;
import lombok.Setter;
import meteor.Main;
import meteor.config.ConfigManager;
import meteor.plugins.EventSubscriber;
import meteor.plugins.PluginManager;
import net.runelite.api.Client;
import net.runelite.api.QuestState;
import net.runelite.client.plugins.questhelper.steps.OwnerStep;
import net.runelite.client.plugins.questhelper.steps.QuestStep;

public abstract class QuestHelper extends EventSubscriber implements Module, QuestDebugRenderer
{
	protected Client client = Main.client;
	@Getter
	protected ConfigManager configManager = ConfigManager.INSTANCE;

	public QuestBank questBank = QuestBank.INSTANCE;

	@Getter
	private QuestStep currentStep;

	@Setter
	public QuestHelperQuest quest;

	public QuestHelperQuest getQuest() {
		return quest;
	}

	@Setter
	@Getter
	protected QuestHelperPlugin questHelperPlugin = (QuestHelperPlugin) PluginManager.INSTANCE.get(QuestHelperPlugin.class);

	@Getter
	@Setter
	public QuestHelperConfig config = questHelperPlugin.getConfig();

	@Override
	public void configure(Binder binder)
	{
	}

	public abstract void init();

	public abstract void startUp(QuestHelperConfig config);

	public abstract void shutDown();

	public abstract boolean updateQuest();

	public void debugStartup(QuestHelperConfig config) {}

	protected void startUpStep(QuestStep step)
	{
		if (step != null)
		{
			currentStep = step;
			currentStep.startUp();
			currentStep.subscribe();
			currentStep.setEventListening(true);
		}
		else
		{
			currentStep = null;
		}
	}

	protected void shutDownStep()
	{
		if (currentStep != null)
		{
			currentStep.unsubscribe();
			currentStep.shutDown();
			currentStep = null;
		}
	}

	protected void instantiateSteps(Collection<QuestStep> steps)
	{
		for (QuestStep step : steps)
		{
			instantiateStep(step);
			if (step instanceof OwnerStep)
			{
				instantiateSteps(((OwnerStep) step).getSteps());
			}
		}
	}

	public void instantiateStep(QuestStep questStep)
	{
		try
		{
			if (questStep != null)
			{
				//injector.injectMembers(questStep);
			}
		}
		catch (CreationException ex)
		{
			ex.printStackTrace();
		}
	}

	public boolean isCompleted()
	{
		return getState(client) == QuestState.FINISHED;
	}

	public QuestState getState(Client client)
	{
		return quest.getState(client);
	}

	public boolean clientMeetsRequirements()
	{
		if (getGeneralRequirements() == null)
		{
			return true;
		}

		return getGeneralRequirements().stream().filter(Objects::nonNull).allMatch(r ->
			!r.shouldConsiderForFilter() || r.check(client));
	}

	public int getVar()
	{
		return quest.getVar(client);
	}

	public abstract void setupRequirements();

	public List<ItemRequirement> getItemRequirements()
	{
		return null;
	}

	public List<Requirement> getGeneralRequirements()
	{
		return null;
	}

	public List<ItemRequirement> getItemRecommended()
	{
		return null;
	}

	public List<Requirement> getGeneralRecommended()
	{
		return null;
	}

	public List<String> getCombatRequirements()
	{
		return null;
	}

	public List<String> getNotes()
	{
		return null;
	}

	public QuestPointReward getQuestPointReward()
	{
		return null;
	}

	public List<ItemReward> getItemRewards()
	{
		return null;
	}

	public List<ExperienceReward> getExperienceRewards()
	{
		return null;
	}

	public List<UnlockReward> getUnlockRewards()
	{
		return null;
	}

	public List<String> getQuestRewards()
	{
		List<String> rewards = new ArrayList<>();

		QuestPointReward questPointReward = getQuestPointReward();
		if (questPointReward != null)
		{
			rewards.add(questPointReward.getDisplayText());
			rewards.add("</br>");
		}

		List<ItemReward> itemRewards = getItemRewards();
		if (itemRewards != null)
		{
			itemRewards.forEach((itemReward -> rewards.add(itemReward.getDisplayText())));
			rewards.add("</br>");
		}

		List<ExperienceReward> experienceReward = getExperienceRewards();
		if (experienceReward != null)
		{
			experienceReward.forEach((expReward -> rewards.add(expReward.getDisplayText())));
			rewards.add("</br>");
		}

		List<UnlockReward> unlockRewards = getUnlockRewards();
		if (unlockRewards != null)
		{
			unlockRewards.forEach((unlockReward -> rewards.add(unlockReward.getDisplayText())));
			rewards.add("</br>");
		}

		return rewards;
	}

	public List<ExternalQuestResources> getExternalResources(){ return null; }

	public abstract List<PanelDetails> getPanels();
}