import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("lf")
public class class303 {
	@ObfuscatedName("fy")
	@ObfuscatedSignature(
		descriptor = "Lgu;"
	)
	@Export("js5SocketTask")
	static Task js5SocketTask;

	@ObfuscatedName("az")
	@ObfuscatedSignature(
		descriptor = "([BIII)Z",
		garbageValue = "756949404"
	)
	static final boolean method1578(byte[] var0, int var1, int var2) {
		boolean var3 = true;
		Buffer var4 = new Buffer(var0);
		int var5 = -1;

		label57:
		while (true) {
			int var6 = var4.method2541();
			if (var6 == 0) {
				return var3;
			}

			var5 += var6;
			int var7 = 0;
			boolean var8 = false;

			while (true) {
				int var9;
				while (!var8) {
					var9 = var4.readUShortSmart();
					if (var9 == 0) {
						continue label57;
					}

					var7 += var9 - 1;
					int var10 = var7 & 63;
					int var11 = var7 >> 6 & 63;
					int var12 = var4.readUnsignedByte() >> 2;
					int var13 = var11 + var1;
					int var14 = var10 + var2;
					if (var13 > 0 && var14 > 0 && var13 < 103 && var14 < 103) {
						ObjectComposition var15 = class90.getObjectDefinition(var5);
						if (var12 != 22 || !Client.isLowDetail || var15.int1 != 0 || var15.interactType == 1 || var15.boolean2) {
							if (!var15.needsModelFiles()) {
								++Client.field448;
								var3 = false;
							}

							var8 = true;
						}
					}
				}

				var9 = var4.readUShortSmart();
				if (var9 == 0) {
					break;
				}

				var4.readUnsignedByte();
			}
		}
	}

	@ObfuscatedName("jq")
	@ObfuscatedSignature(
		descriptor = "(Ldx;IIIIII)V",
		garbageValue = "-219666701"
	)
	@Export("drawActor2d")
	static final void drawActor2d(Actor var0, int var1, int var2, int var3, int var4, int var5) {
		if (var0 != null && var0.isVisible()) {
			if (var0 instanceof NPC) {
				NPCComposition var6 = ((NPC)var0).definition;
				if (var6.transforms != null) {
					var6 = var6.transform();
				}

				if (var6 == null) {
					return;
				}
			}

			int var75 = Players.Players_count;
			int[] var7 = Players.Players_indices;
			boolean var8 = var1 < var75;
			int var9 = -2;
			if (var0.overheadText != null && (!var8 || !var0.showPublicPlayerChat && (Client.publicChatMode == 4 || !var0.isAutoChatting && (Client.publicChatMode == 0 || Client.publicChatMode == 3 || Client.publicChatMode == 1 && ((Player)var0).isFriend())))) {
				class141.method754(var0, var0.defaultHeight);
				if (Client.viewportTempX > -1 && Client.overheadTextLimit < Client.overheadTextCount) {
					Client.overheadTextXOffsets[Client.overheadTextLimit] = Calendar.fontBold12.stringWidth(var0.overheadText) / 2;
					Client.overheadTextAscents[Client.overheadTextLimit] = Calendar.fontBold12.ascent;
					Client.overheadTextXs[Client.overheadTextLimit] = Client.viewportTempX;
					Client.overheadTextYs[Client.overheadTextLimit] = Client.viewportTempY - var9;
					Client.overheadTextColors[Client.overheadTextLimit] = var0.field986;
					Client.overheadTextEffects[Client.overheadTextLimit] = var0.field994;
					Client.overheadTextCyclesRemaining[Client.overheadTextLimit] = var0.overheadTextCyclesRemaining;
					Client.field591[Client.overheadTextLimit] = var0.field1023;
					Client.field599[Client.overheadTextLimit] = var0.overheadText;
					++Client.overheadTextLimit;
					var9 += 12;
				}
			}

			int var15;
			int var22;
			int var23;
			if (!var0.healthBars.method1971()) {
				class141.method754(var0, var0.defaultHeight + 15);

				for (HealthBar var10 = (HealthBar)var0.healthBars.last(); var10 != null; var10 = (HealthBar)var0.healthBars.previous()) {
					HealthBarUpdate var11 = var10.get(Client.cycle);
					if (var11 == null) {
						if (var10.isEmpty()) {
							var10.remove();
						}
					} else {
						HealthBarDefinition var12 = var10.definition;
						SpritePixels var13 = var12.getBackSprite();
						SpritePixels var14 = var12.getFrontSprite();
						int var16 = 0;
						if (var13 != null && var14 != null) {
							if (var12.widthPadding * 2 < var14.subWidth) {
								var16 = var12.widthPadding;
							}

							var15 = var14.subWidth - var16 * 2;
						} else {
							var15 = var12.width;
						}

						int var84 = 255;
						boolean var86 = true;
						int var87 = Client.cycle - var11.cycle;
						int var88 = var15 * var11.health2 / var12.width;
						int var89;
						int var99;
						if (var11.cycleOffset > var87) {
							var89 = var12.field1540 == 0 ? 0 : var12.field1540 * (var87 / var12.field1540);
							var22 = var15 * var11.health / var12.width;
							var99 = var89 * (var88 - var22) / var11.cycleOffset + var22;
						} else {
							var99 = var88;
							var89 = var11.cycleOffset + var12.int5 - var87;
							if (var12.int3 >= 0) {
								var84 = (var89 << 8) / (var12.int5 - var12.int3);
							}
						}

						if (var11.health2 > 0 && var99 < 1) {
							var99 = 1;
						}

						if (var13 != null && var14 != null) {
							if (var15 == var99) {
								var99 += var16 * 2;
							} else {
								var99 += var16;
							}

							var89 = var13.subHeight;
							var9 += var89;
							var22 = var2 + Client.viewportTempX - (var15 >> 1);
							var23 = var3 + Client.viewportTempY - var9;
							var22 -= var16;
							if (var84 >= 0 && var84 < 255) {
								var13.drawTransAt(var22, var23, var84);
								Rasterizer2D.Rasterizer2D_expandClip(var22, var23, var22 + var99, var23 + var89);
								var14.drawTransAt(var22, var23, var84);
							} else {
								var13.drawTransBgAt(var22, var23);
								Rasterizer2D.Rasterizer2D_expandClip(var22, var23, var22 + var99, var23 + var89);
								var14.drawTransBgAt(var22, var23);
							}

							Rasterizer2D.Rasterizer2D_setClip(var2, var3, var2 + var4, var3 + var5);
							var9 += 2;
						} else {
							var9 += 5;
							if (Client.viewportTempX > -1) {
								var89 = var2 + Client.viewportTempX - (var15 >> 1);
								var22 = var3 + Client.viewportTempY - var9;
								Rasterizer2D.Rasterizer2D_fillRectangle(var89, var22, var99, 5, 65280);
								Rasterizer2D.Rasterizer2D_fillRectangle(var99 + var89, var22, var15 - var99, 5, 16711680);
							}

							var9 += 2;
						}
					}
				}
			}

			if (var9 == -2) {
				var9 += 7;
			}

			Player var90;
			if (var8 && var0.playerCycle == Client.cycle && class60.method330((Player)var0)) {
				var90 = (Player)var0;
				if (var8) {
					class141.method754(var0, var0.defaultHeight + 15);
					AbstractFont var91 = (AbstractFont)Client.fontsMap.get(FontName.FontName_plain12);
					var9 += 4;
					var91.drawCentered(var90.username.getName(), var2 + Client.viewportTempX, var3 + Client.viewportTempY - var9, 16777215, 0);
					var9 += 18;
				}
			}

			if (var8) {
				var90 = (Player)var0;
				if (var90.isHidden) {
					return;
				}

				if (var90.headIconPk != -1 || var90.headIconPrayer != -1) {
					class141.method754(var0, var0.defaultHeight + 15);
					if (Client.viewportTempX > -1) {
						if (var90.headIconPk != -1) {
							var9 += 25;
							DbTableType.headIconPkSprites[var90.headIconPk].drawTransBgAt(var2 + Client.viewportTempX - 12, var3 + Client.viewportTempY - var9);
						}

						if (var90.headIconPrayer != -1) {
							var9 += 25;
							class17.headIconPrayerSprites[var90.headIconPrayer].drawTransBgAt(var2 + Client.viewportTempX - 12, var3 + Client.viewportTempY - var9);
						}
					}
				}

				if (var1 >= 0 && Client.hintArrowType == 10 && var7[var1] == Client.hintArrowPlayerIndex) {
					class141.method754(var0, var0.defaultHeight + 15);
					if (Client.viewportTempX > -1) {
						var9 += class345.headIconHintSprites[1].subHeight;
						class345.headIconHintSprites[1].drawTransBgAt(var2 + Client.viewportTempX - 12, var3 + Client.viewportTempY - var9);
					}
				}
			} else {
				NPC var92 = (NPC)var0;
				int[] var93 = var92.method591();
				short[] var94 = var92.method592();
				if (var94 != null && var93 != null) {
					for (int var76 = 0; var76 < var94.length; ++var76) {
						if (var94[var76] >= 0 && var93[var76] >= 0) {
							long var77 = (long)var93[var76] << 8 | (long)var94[var76];
							SpritePixels var79 = (SpritePixels)Client.archive5.method2146(var77);
							if (var79 == null) {
								SpritePixels[] var17 = class453.method2294(class386.archive8, var93[var76], 0);
								if (var17 != null && var94[var76] < var17.length) {
									var79 = var17[var94[var76]];
									Client.archive5.method2147(var77, var79);
								}
							}

							if (var79 != null) {
								class141.method754(var0, var0.defaultHeight + 15);
								if (Client.viewportTempX > -1) {
									var79.drawTransBgAt(var2 + Client.viewportTempX - (var79.subWidth >> 1), Client.viewportTempY + (var3 - var79.subHeight) - 4);
								}
							}
						}
					}
				}

				if (Client.hintArrowType == 1 && Client.npcIndices[var1 - var75] == Client.hintArrowNpcIndex && Client.cycle % 20 < 10) {
					class141.method754(var0, var0.defaultHeight + 15);
					if (Client.viewportTempX > -1) {
						class345.headIconHintSprites[0].drawTransBgAt(var2 + Client.viewportTempX - 12, var3 + Client.viewportTempY - 28);
					}
				}
			}

			for (int var80 = 0; var80 < 4; ++var80) {
				int var81 = var0.hitSplatCycles[var80];
				int var82 = var0.hitSplatTypes[var80];
				HitSplatDefinition var95 = null;
				int var83 = 0;
				if (var82 >= 0) {
					if (var81 <= Client.cycle) {
						continue;
					}

					var95 = class177.method907(var0.hitSplatTypes[var80]);
					var83 = var95.field1687;
					if (var95 != null && var95.transforms != null) {
						var95 = var95.transform();
						if (var95 == null) {
							var0.hitSplatCycles[var80] = -1;
							continue;
						}
					}
				} else if (var81 < 0) {
					continue;
				}

				var15 = var0.hitSplatTypes2[var80];
				HitSplatDefinition var97 = null;
				if (var15 >= 0) {
					var97 = class177.method907(var15);
					if (var97 != null && var97.transforms != null) {
						var97 = var97.transform();
					}
				}

				if (var81 - var83 <= Client.cycle) {
					if (var95 == null) {
						var0.hitSplatCycles[var80] = -1;
					} else {
						class141.method754(var0, var0.defaultHeight / 2);
						if (Client.viewportTempX > -1) {
							boolean var98 = true;
							if (var80 == 1) {
								Client.viewportTempY -= 20;
							}

							if (var80 == 2) {
								Client.viewportTempX -= 15;
								Client.viewportTempY -= 10;
							}

							if (var80 == 3) {
								Client.viewportTempX += 15;
								Client.viewportTempY -= 10;
							}

							SpritePixels var18 = null;
							SpritePixels var19 = null;
							SpritePixels var20 = null;
							SpritePixels var21 = null;
							var22 = 0;
							var23 = 0;
							int var24 = 0;
							int var25 = 0;
							int var26 = 0;
							int var27 = 0;
							int var28 = 0;
							int var29 = 0;
							SpritePixels var30 = null;
							SpritePixels var31 = null;
							SpritePixels var32 = null;
							SpritePixels var33 = null;
							int var34 = 0;
							int var35 = 0;
							int var36 = 0;
							int var37 = 0;
							int var38 = 0;
							int var39 = 0;
							int var40 = 0;
							int var41 = 0;
							int var42 = 0;
							var18 = var95.method1012();
							int var43;
							if (var18 != null) {
								var22 = var18.subWidth;
								var43 = var18.subHeight;
								if (var43 > var42) {
									var42 = var43;
								}

								var26 = var18.xOffset;
							}

							var19 = var95.method1013();
							if (var19 != null) {
								var23 = var19.subWidth;
								var43 = var19.subHeight;
								if (var43 > var42) {
									var42 = var43;
								}

								var27 = var19.xOffset;
							}

							var20 = var95.method1014();
							if (var20 != null) {
								var24 = var20.subWidth;
								var43 = var20.subHeight;
								if (var43 > var42) {
									var42 = var43;
								}

								var28 = var20.xOffset;
							}

							var21 = var95.method1015();
							if (var21 != null) {
								var25 = var21.subWidth;
								var43 = var21.subHeight;
								if (var43 > var42) {
									var42 = var43;
								}

								var29 = var21.xOffset;
							}

							if (var97 != null) {
								var30 = var97.method1012();
								if (var30 != null) {
									var34 = var30.subWidth;
									var43 = var30.subHeight;
									if (var43 > var42) {
										var42 = var43;
									}

									var38 = var30.xOffset;
								}

								var31 = var97.method1013();
								if (var31 != null) {
									var35 = var31.subWidth;
									var43 = var31.subHeight;
									if (var43 > var42) {
										var42 = var43;
									}

									var39 = var31.xOffset;
								}

								var32 = var97.method1014();
								if (var32 != null) {
									var36 = var32.subWidth;
									var43 = var32.subHeight;
									if (var43 > var42) {
										var42 = var43;
									}

									var40 = var32.xOffset;
								}

								var33 = var97.method1015();
								if (var33 != null) {
									var37 = var33.subWidth;
									var43 = var33.subHeight;
									if (var43 > var42) {
										var42 = var43;
									}

									var41 = var33.xOffset;
								}
							}

							Font var85 = var95.getFont();
							if (var85 == null) {
								var85 = ItemComposition.fontPlain11;
							}

							Font var44;
							if (var97 != null) {
								var44 = var97.getFont();
								if (var44 == null) {
									var44 = ItemComposition.fontPlain11;
								}
							} else {
								var44 = ItemComposition.fontPlain11;
							}

							String var45 = null;
							String var46 = null;
							boolean var47 = false;
							int var48 = 0;
							var45 = var95.getString(var0.hitSplatValues[var80]);
							int var96 = var85.stringWidth(var45);
							if (var97 != null) {
								var46 = var97.getString(var0.hitSplatValues2[var80]);
								var48 = var44.stringWidth(var46);
							}

							int var49 = 0;
							int var50 = 0;
							if (var23 > 0) {
								if (var20 == null && var21 == null) {
									var49 = 1;
								} else {
									var49 = var96 / var23 + 1;
								}
							}

							if (var97 != null && var35 > 0) {
								if (var32 == null && var33 == null) {
									var50 = 1;
								} else {
									var50 = var48 / var35 + 1;
								}
							}

							int var51 = 0;
							int var52 = var51;
							if (var22 > 0) {
								var51 += var22;
							}

							var51 += 2;
							int var53 = var51;
							if (var24 > 0) {
								var51 += var24;
							}

							int var54 = var51;
							int var55 = var51;
							int var56;
							if (var23 > 0) {
								var56 = var49 * var23;
								var51 += var56;
								var55 += (var56 - var96) / 2;
							} else {
								var51 += var96;
							}

							var56 = var51;
							if (var25 > 0) {
								var51 += var25;
							}

							int var57 = 0;
							int var58 = 0;
							int var59 = 0;
							int var60 = 0;
							int var61 = 0;
							int var62;
							if (var97 != null) {
								var51 += 2;
								var57 = var51;
								if (var34 > 0) {
									var51 += var34;
								}

								var51 += 2;
								var58 = var51;
								if (var36 > 0) {
									var51 += var36;
								}

								var59 = var51;
								var61 = var51;
								if (var35 > 0) {
									var62 = var35 * var50;
									var51 += var62;
									var61 += (var62 - var48) / 2;
								} else {
									var51 += var48;
								}

								var60 = var51;
								if (var37 > 0) {
									var51 += var37;
								}
							}

							var62 = var0.hitSplatCycles[var80] - Client.cycle;
							int var63 = var95.field1682 - var62 * var95.field1682 / var95.field1687;
							int var64 = var62 * var95.field1686 / var95.field1687 + -var95.field1686;
							int var65 = var63 + (var2 + Client.viewportTempX - (var51 >> 1));
							int var66 = var3 + Client.viewportTempY - 12 + var64;
							int var67 = var66;
							int var68 = var66 + var42;
							int var69 = var66 + var95.field1683 + 15;
							int var70 = var69 - var85.maxAscent;
							int var71 = var69 + var85.maxDescent;
							if (var70 < var66) {
								var67 = var70;
							}

							if (var71 > var68) {
								var68 = var71;
							}

							int var72 = 0;
							int var73;
							int var74;
							if (var97 != null) {
								var72 = var66 + var97.field1683 + 15;
								var73 = var72 - var44.maxAscent;
								var74 = var72 + var44.maxDescent;
								if (var73 < var67) {
									;
								}

								if (var74 > var68) {
									;
								}
							}

							var73 = 255;
							if (var95.field1681 >= 0) {
								var73 = (var62 << 8) / (var95.field1687 - var95.field1681);
							}

							if (var73 >= 0 && var73 < 255) {
								if (var18 != null) {
									var18.drawTransAt(var52 + var65 - var26, var66, var73);
								}

								if (var20 != null) {
									var20.drawTransAt(var53 + var65 - var28, var66, var73);
								}

								if (var19 != null) {
									for (var74 = 0; var74 < var49; ++var74) {
										var19.drawTransAt(var74 * var23 + (var54 + var65 - var27), var66, var73);
									}
								}

								if (var21 != null) {
									var21.drawTransAt(var65 + var56 - var29, var66, var73);
								}

								var85.drawAlpha(var45, var65 + var55, var69, var95.textColor, 0, var73);
								if (var97 != null) {
									if (var30 != null) {
										var30.drawTransAt(var65 + var57 - var38, var66, var73);
									}

									if (var32 != null) {
										var32.drawTransAt(var65 + var58 - var40, var66, var73);
									}

									if (var31 != null) {
										for (var74 = 0; var74 < var50; ++var74) {
											var31.drawTransAt(var74 * var35 + (var65 + var59 - var39), var66, var73);
										}
									}

									if (var33 != null) {
										var33.drawTransAt(var65 + var60 - var41, var66, var73);
									}

									var44.drawAlpha(var46, var61 + var65, var72, var97.textColor, 0, var73);
								}
							} else {
								if (var18 != null) {
									var18.drawTransBgAt(var52 + var65 - var26, var66);
								}

								if (var20 != null) {
									var20.drawTransBgAt(var53 + var65 - var28, var66);
								}

								if (var19 != null) {
									for (var74 = 0; var74 < var49; ++var74) {
										var19.drawTransBgAt(var23 * var74 + (var54 + var65 - var27), var66);
									}
								}

								if (var21 != null) {
									var21.drawTransBgAt(var65 + var56 - var29, var66);
								}

								var85.draw(var45, var55 + var65, var69, var95.textColor | -16777216, 0);
								if (var97 != null) {
									if (var30 != null) {
										var30.drawTransBgAt(var65 + var57 - var38, var66);
									}

									if (var32 != null) {
										var32.drawTransBgAt(var65 + var58 - var40, var66);
									}

									if (var31 != null) {
										for (var74 = 0; var74 < var50; ++var74) {
											var31.drawTransBgAt(var35 * var74 + (var59 + var65 - var39), var66);
										}
									}

									if (var33 != null) {
										var33.drawTransBgAt(var60 + var65 - var41, var66);
									}

									var44.draw(var46, var65 + var61, var72, var97.textColor | -16777216, 0);
								}
							}
						}
					}
				}
			}

		}
	}

	@ObfuscatedName("jz")
	@ObfuscatedSignature(
		descriptor = "(ZLtc;B)V",
		garbageValue = "46"
	)
	@Export("loadRegions")
	static final void loadRegions(boolean var0, PacketBuffer var1) {
		Client.isInInstance = var0;
		int var2;
		int var3;
		int var5;
		int var6;
		int var7;
		if (!Client.isInInstance) {
			var2 = var1.readUnsignedShort(); // Z
			var3 = var1.readUnsignedShort(); // X

			System.out.println("Z: " + var2);
			System.out.println("X: " + var3);
			int var4 = var1.readUnsignedShort();
			class18.xteaKeys = new int[var4][4];

			for (var5 = 0; var5 < var4; ++var5) {
				for (var6 = 0; var6 < 4; ++var6) {
					class18.xteaKeys[var5][var6] = var1.readInt();
				}
			}

			MusicPatch.regions = new int[var4];
			UserComparator4.regionMapArchiveIds = new int[var4];
			class170.regionLandArchiveIds = new int[var4];
			class155.regionLandArchives = new byte[var4][];
			class389.regionMapArchives = new byte[var4][];
			var4 = 0;

			for (var5 = (var3 - 6) / 8; var5 <= (var3 + 6) / 8; ++var5) {
				for (var6 = (var2 - 6) / 8; var6 <= (var2 + 6) / 8; ++var6) {
					var7 = var6 + (var5 << 8);
					MusicPatch.regions[var4] = var7;
					UserComparator4.regionMapArchiveIds[var4] = Canvas.archive9.getGroupId("m" + var5 + "_" + var6);
					class170.regionLandArchiveIds[var4] = Canvas.archive9.getGroupId("l" + var5 + "_" + var6);
					++var4;
				}
			}

			class385.method1984(var3, var2, true);  // X Z
		} else {
			var2 = var1.readUnsignedShortAdd();
			var3 = var1.readUnsignedShortLE();
			boolean var15 = var1.readUnsignedByteSub() == 1;
			var5 = var1.readUnsignedShort();
			System.out.println("Rebuild_region:");
			System.out.println("var2: " + var2);
			System.out.println("var3: " + var3);
			System.out.println("var15: " + var15);
			System.out.println("var5: " + var5);

			var1.importIndex();

			int var8;
			int var9;
			for (var6 = 0; var6 < 4; ++var6) {
				for (var7 = 0; var7 < 13; ++var7) {
					for (var8 = 0; var8 < 13; ++var8) {
						var9 = var1.readBits(1);
						if (var9 == 1) {
							Client.instanceChunkTemplates[var6][var7][var8] = var1.readBits(26);
						} else {
							Client.instanceChunkTemplates[var6][var7][var8] = -1;
						}
					}
				}
			}

			var1.exportIndex();
			class18.xteaKeys = new int[var5][4];

			for (var6 = 0; var6 < var5; ++var6) {
				for (var7 = 0; var7 < 4; ++var7) {
					class18.xteaKeys[var6][var7] = var1.readInt();
				}
			}

			MusicPatch.regions = new int[var5];
			UserComparator4.regionMapArchiveIds = new int[var5];
			class170.regionLandArchiveIds = new int[var5];
			class155.regionLandArchives = new byte[var5][];
			class389.regionMapArchives = new byte[var5][];
			var5 = 0;

			for (var6 = 0; var6 < 4; ++var6) {
				for (var7 = 0; var7 < 13; ++var7) {
					for (var8 = 0; var8 < 13; ++var8) {
						var9 = Client.instanceChunkTemplates[var6][var7][var8];
						if (var9 != -1) {
							int var10 = var9 >> 14 & 1023;
							int var11 = var9 >> 3 & 2047;
							int var12 = (var10 / 8 << 8) + var11 / 8;

							int var13;
							for (var13 = 0; var13 < var5; ++var13) {
								if (MusicPatch.regions[var13] == var12) {
									var12 = -1;
									break;
								}
							}

							if (var12 != -1) {
								MusicPatch.regions[var5] = var12;
								var13 = var12 >> 8 & 255;
								int var14 = var12 & 255;
								UserComparator4.regionMapArchiveIds[var5] = Canvas.archive9.getGroupId("m" + var13 + "_" + var14);
								class170.regionLandArchiveIds[var5] = Canvas.archive9.getGroupId("l" + var13 + "_" + var14);
								++var5;
							}
						}
					}
				}
			}

			class385.method1984(var2, var3, !var15); // X Z
		}

	}
}
