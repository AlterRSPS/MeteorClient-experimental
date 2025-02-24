import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("gk")
@Implements("FileSystem")
public class FileSystem {
	@ObfuscatedName("aw")
	@Export("FileSystem_hasPermissions")
	static boolean FileSystem_hasPermissions;
	@ObfuscatedName("ay")
	@Export("FileSystem_cacheDir")
	static File FileSystem_cacheDir;
	@ObfuscatedName("ar")
	@Export("FileSystem_cacheFiles")
	public static Hashtable FileSystem_cacheFiles;

	static {
		FileSystem_hasPermissions = false;
		FileSystem_cacheFiles = new Hashtable(16);
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(Lsa;III)I",
		garbageValue = "503830382"
	)
	static int method904(IterableNodeHashTable var0, int var1, int var2) {
		if (var0 == null) {
			return var2;
		} else {
			IntegerNode var3 = (IntegerNode)var0.get((long)var1);
			return var3 == null ? var2 : var3.integer;
		}
	}

	@ObfuscatedName("az")
	@ObfuscatedSignature(
		descriptor = "(Lbq;Lpc;Lpc;I)V",
		garbageValue = "-2089610118"
	)
	static void method906(GameEngine var0, Font var1, Font var2) {
		if (Login.worldSelectOpen) {
			class20.method75(var0);
		} else {
			if ((MouseHandler.MouseHandler_lastButton == 1 || !class28.mouseCam && MouseHandler.MouseHandler_lastButton == 4) && MouseHandler.MouseHandler_lastPressedX >= Login.xPadding + 765 - 50 && MouseHandler.MouseHandler_lastPressedY >= 453) {
				class150.clientPreferences.getTitleMusicDisabled(!class150.clientPreferences.getTitleMusicDisabled());
				if (!class150.clientPreferences.getTitleMusicDisabled()) {
					ArrayList var3 = new ArrayList();
					var3.add(new MusicSong(class13.archive6, "scape main", "", 255, false));
					UserComparator4.method663(var3, 0, 0, 0, 100, false);
				} else {
					RouteStrategy.method1107(0, 0);
				}

				class306.field2755.clear();
			}

			if (Client.gameState != 5) {
				if (Login.field772 == -1L) {
					Login.field772 = class113.clockNow() + 1000L;
				}

				long var23 = class113.clockNow();
				if (class28.method119() && -1L == Login.field771) {
					Login.field771 = var23;
					if (Login.field771 > Login.field772) {
						Login.field772 = Login.field771;
					}
				}

				if (Client.gameState == 10 || Client.gameState == 11) {
					int var5;
					if (Language.Language_EN == Interpreter.clientLanguage) {
						if (MouseHandler.MouseHandler_lastButton == 1 || !class28.mouseCam && MouseHandler.MouseHandler_lastButton == 4) {
							var5 = Login.xPadding + 5;
							short var6 = 463;
							byte var7 = 100;
							byte var8 = 35;
							if (MouseHandler.MouseHandler_lastPressedX >= var5 && MouseHandler.MouseHandler_lastPressedX <= var5 + var7 && MouseHandler.MouseHandler_lastPressedY >= var6 && MouseHandler.MouseHandler_lastPressedY <= var8 + var6) {
								if (DynamicObject.loadWorlds()) {
									Login.worldSelectOpen = true;
									Login.worldSelectPage = 0;
									Login.worldSelectPagesCount = 0;
								}

								return;
							}
						}

						if (World.World_request != null && DynamicObject.loadWorlds()) {
							Login.worldSelectOpen = true;
							Login.worldSelectPage = 0;
							Login.worldSelectPagesCount = 0;
						}
					}

					var5 = MouseHandler.MouseHandler_lastButton;
					int var41 = MouseHandler.MouseHandler_lastPressedX;
					int var42 = MouseHandler.MouseHandler_lastPressedY;
					if (var5 == 0) {
						var41 = MouseHandler.MouseHandler_x;
						var42 = MouseHandler.MouseHandler_y;
					}

					if (!class28.mouseCam && var5 == 4) {
						var5 = 1;
					}

					class213 var25 = SceneTilePaint.method1324();
					short var44;
					int var45;
					if (Login.loginIndex == 0) {
						boolean var50 = false;

						while (var25.method1085()) {
							if (var25.field1869 == 84) {
								var50 = true;
							}
						}

						var45 = Script.loginBoxCenter - 80;
						var44 = 291;
						if (var5 == 1 && var41 >= var45 - 75 && var41 <= var45 + 75 && var42 >= var44 - 20 && var42 <= var44 + 20) {
							NPC.openURL(UrlRequest.method661("secure", true) + "m=account-creation/g=oldscape/create_account_funnel.ws", true, false);
						}

						var45 = Script.loginBoxCenter + 80;
						if (var5 == 1 && var41 >= var45 - 75 && var41 <= var45 + 75 && var42 >= var44 - 20 && var42 <= var44 + 20 || var50) {
							if ((Client.worldProperties & 33554432) != 0) {
								Login.Login_response0 = "";
								Login.Login_response1 = "This is a <col=00ffff>Beta<col=ffffff> world.";
								Login.Login_response2 = "Your normal account will not be affected.";
								Login.Login_response3 = "";
								class328.method1678(1);
								class395.method2020();
							} else if ((Client.worldProperties & 4) != 0) {
								if ((Client.worldProperties & 1024) != 0) {
									Login.Login_response1 = "This is a <col=ffff00>High Risk <col=ff0000>PvP<col=ffffff> world.";
									Login.Login_response2 = "Players can attack each other almost everywhere";
									Login.Login_response3 = "and the Protect Item prayer won't work.";
								} else {
									Login.Login_response1 = "This is a <col=ff0000>PvP<col=ffffff> world.";
									Login.Login_response2 = "Players can attack each other";
									Login.Login_response3 = "almost everywhere.";
								}

								Login.Login_response0 = "Warning!";
								class328.method1678(1);
								class395.method2020();
							} else if ((Client.worldProperties & 1024) != 0) {
								Login.Login_response1 = "This is a <col=ffff00>High Risk<col=ffffff> world.";
								Login.Login_response2 = "The Protect Item prayer will";
								Login.Login_response3 = "not work on this world.";
								Login.Login_response0 = "Warning!";
								class328.method1678(1);
								class395.method2020();
							} else {
								PlayerComposition.Login_promptCredentials(false);
							}
						}
					} else {
						int var9;
						short var10;
						if (Login.loginIndex == 1) {
							while (true) {
								if (!var25.method1085()) {
									var9 = Script.loginBoxCenter - 80;
									var10 = 321;
									if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
										PlayerComposition.Login_promptCredentials(false);
									}

									var9 = Script.loginBoxCenter + 80;
									if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
										class328.method1678(0);
									}
									break;
								}

								if (var25.field1869 == 84) {
									PlayerComposition.Login_promptCredentials(false);
								} else if (var25.field1869 == 13) {
									class328.method1678(0);
								}
							}
						} else {
							short var43;
							boolean var46;
							if (Login.loginIndex == 2) {
								var43 = 201;
								var9 = var43 + 52;
								if (var5 == 1 && var42 >= var9 - 12 && var42 < var9 + 2) {
									Login.currentLoginField = 0;
								}

								var9 += 15;
								if (var5 == 1 && var42 >= var9 - 12 && var42 < var9 + 2) {
									Login.currentLoginField = 1;
								}

								var9 += 15;
								var43 = 361;
								if (class343.field3130 != null) {
									var45 = class343.field3130.highX / 2;
									if (var5 == 1 && var41 >= class343.field3130.lowX - var45 && var41 <= var45 + class343.field3130.lowX && var42 >= var43 - 15 && var42 < var43) {
										switch(Login.field747) {
										case 1:
											Actor.setLoginResponseString("Please enter your username.", "If you created your account after November", "2010, this will be the creation email address.");
											class328.method1678(5);
											return;
										case 2:
											NPC.openURL("https://support.runescape.com/hc/en-gb", true, false);
										}
									}
								}

								var45 = Script.loginBoxCenter - 80;
								var44 = 321;
								if (var5 == 1 && var41 >= var45 - 75 && var41 <= var45 + 75 && var42 >= var44 - 20 && var42 <= var44 + 20) {
									Login.Login_username = Login.Login_username.trim();
									if (Login.Login_username.length() == 0) {
										Actor.setLoginResponseString("", "Please enter your username/email address.", "");
										return;
									}

									if (Login.Login_password.length() == 0) {
										Actor.setLoginResponseString("", "Please enter your password.", "");
										return;
									}

									Actor.setLoginResponseString("", "Connecting to server...", "");
									GrandExchangeOfferTotalQuantityComparator.method1911(false);
									class129.updateGameState(20);
									return;
								}

								var45 = Login.loginBoxX + 180 + 80;
								if (var5 == 1 && var41 >= var45 - 75 && var41 <= var45 + 75 && var42 >= var44 - 20 && var42 <= var44 + 20) {
									class328.method1678(0);
									Login.Login_username = "";
									Login.Login_password = "";
									class424.field3758 = 0;
									Login.otp = "";
									Login.field739 = true;
								}

								var45 = Script.loginBoxCenter + -117;
								var44 = 277;
								Login.field741 = var41 >= var45 && var41 < var45 + class139.field1311 && var42 >= var44 && var42 < var44 + class453.field3874;
								if (var5 == 1 && Login.field741) {
									Client.Login_isUsernameRemembered = !Client.Login_isUsernameRemembered;
									if (!Client.Login_isUsernameRemembered && class150.clientPreferences.getUsernameToRemember() != null) {
										class150.clientPreferences.setUsernameToRemember((String)null);
									}
								}

								var45 = Script.loginBoxCenter + 24;
								var44 = 277;
								Login.field740 = var41 >= var45 && var41 < var45 + class139.field1311 && var42 >= var44 && var42 < var44 + class453.field3874;
								if (var5 == 1 && Login.field740) {
									class150.clientPreferences.setIsUsernameHidden(!class150.clientPreferences.getIsUsernameHidden());
									if (!class150.clientPreferences.getIsUsernameHidden()) {
										Login.Login_username = "";
										class150.clientPreferences.setUsernameToRemember((String)null);
										class395.method2020();
									}
								}

								label1056:
								while (true) {
									int var48;
									Transferable var54;
									do {
										while (true) {
											char var47;
											label997:
											do {
												while (true) {
													while (var25.method1085()) {
														if (var25.field1869 != 13) {
															if (Login.currentLoginField != 0) {
																continue label997;
															}

															class60.method329(var25.field1867);
															if (var25.field1869 == 85 && Login.Login_username.length() > 0) {
																Login.Login_username = Login.Login_username.substring(0, Login.Login_username.length() - 1);
															}

															if (var25.field1869 == 84 || var25.field1869 == 80) {
																Login.currentLoginField = 1;
															}

															var47 = var25.field1867;
															var46 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"ï¿½$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".indexOf(var47) != -1;
															if (var46 && Login.Login_username.length() < 320) {
																Login.Login_username = Login.Login_username + var25.field1867;
															}
														} else {
															class328.method1678(0);
															Login.Login_username = "";
															Login.Login_password = "";
															class424.field3758 = 0;
															Login.otp = "";
															Login.field739 = true;
														}
													}

													return;
												}
											} while(Login.currentLoginField != 1);

											if (var25.field1869 == 85 && Login.Login_password.length() > 0) {
												Login.Login_password = Login.Login_password.substring(0, Login.Login_password.length() - 1);
											} else if (var25.field1869 == 84 || var25.field1869 == 80) {
												Login.currentLoginField = 0;
												if (var25.field1869 == 84) {
													Login.Login_username = Login.Login_username.trim();
													if (Login.Login_username.length() == 0) {
														Actor.setLoginResponseString("", "Please enter your username/email address.", "");
														return;
													}

													if (Login.Login_password.length() == 0) {
														Actor.setLoginResponseString("", "Please enter your password.", "");
														return;
													}

													Actor.setLoginResponseString("", "Connecting to server...", "");
													GrandExchangeOfferTotalQuantityComparator.method1911(false);
													class129.updateGameState(20);
													return;
												}
											}

											if ((var25.getKeyPressed(82) || var25.getKeyPressed(87)) && var25.field1869 == 67) {
												Clipboard var53 = Toolkit.getDefaultToolkit().getSystemClipboard();
												var54 = var53.getContents(TileItem.client);
												var48 = 20 - Login.Login_password.length();
												break;
											}

											if (Script.method449(var25.field1867)) {
												var47 = var25.field1867;
												var46 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"ï¿½$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".indexOf(var47) != -1;
												if (var46 && Login.Login_password.length() < 20) {
													Login.Login_password = Login.Login_password + var25.field1867;
												}
											}
										}
									} while(var48 <= 0);

									try {
										String var15 = (String)var54.getTransferData(DataFlavor.stringFlavor);
										int var16 = Math.min(var48, var15.length());
										int var49 = 0;

										while (true) {
											if (var49 >= var16) {
												Login.Login_password = Login.Login_password + var15.substring(0, var16);
												continue label1056;
											}

											char var51 = var15.charAt(var49);
											boolean var18;
											if ((var51 < ' ' || var51 >= 127) && (var51 <= 127 || var51 >= 160) && (var51 <= 160 || var51 > 255)) {
												label1184: {
													if (var51 != 0) {
														char[] var20 = class385.cp1252AsciiExtension;

														for (int var21 = 0; var21 < var20.length; ++var21) {
															char var22 = var20[var21];
															if (var22 == var51) {
																var18 = true;
																break label1184;
															}
														}
													}

													var18 = false;
												}
											} else {
												var18 = true;
											}

											if (!var18) {
												break;
											}

											char var52 = var15.charAt(var49);
											boolean var28 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"ï¿½$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".indexOf(var52) != -1;
											if (!var28) {
												break;
											}

											++var49;
										}

										class328.method1678(3);
										return;
									} catch (UnsupportedFlavorException var39) {
									} catch (IOException var40) {
									}
								}
							} else {
								Bounds var29;
								if (Login.loginIndex == 3) {
									var9 = Login.loginBoxX + 180;
									var10 = 241;
									var29 = var1.method2048(25, "need to log in using the <u=ffd200><col=ffd200>Jagex Launcher</col></u> instead.".length() - 34, "need to log in using the <u=ffd200><col=ffd200>Jagex Launcher</col></u> instead.", var9, var10);
									if (var5 == 1 && var29.method2257(var41, var42)) {
										NPC.openURL("https://oldschool.runescape.com/launcher", true, false);
									}

									var9 = Login.loginBoxX + 180;
									var10 = 276;
									if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
										class333.method1811(false);
									}

									var9 = Login.loginBoxX + 180;
									var10 = 326;
									if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
										Actor.setLoginResponseString("Please enter your username.", "If you created your account after November", "2010, this will be the creation email address.");
										class328.method1678(5);
										return;
									}
								} else {
									int var12;
									if (Login.loginIndex == 4) {
										var9 = Login.loginBoxX + 180 - 80;
										var10 = 321;
										if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
											Login.otp.trim();
											if (Login.otp.length() != 6) {
												Actor.setLoginResponseString("", "Please enter a 6-digit PIN.", "");
												return;
											}

											class424.field3758 = Integer.parseInt(Login.otp);
											Login.otp = "";
											GrandExchangeOfferTotalQuantityComparator.method1911(true);
											Actor.setLoginResponseString("", "Connecting to server...", "");
											class129.updateGameState(20);
											return;
										}

										if (var5 == 1 && var41 >= Login.loginBoxX + 180 - 9 && var41 <= Login.loginBoxX + 180 + 130 && var42 >= 263 && var42 <= 296) {
											Login.field739 = !Login.field739;
										}

										if (var5 == 1 && var41 >= Login.loginBoxX + 180 - 34 && var41 <= Login.loginBoxX + 34 + 180 && var42 >= 351 && var42 <= 363) {
											NPC.openURL(UrlRequest.method661("secure", true) + "m=totp-authenticator/disableTOTPRequest", true, false);
										}

										var9 = Login.loginBoxX + 180 + 80;
										if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
											class328.method1678(0);
											Login.Login_username = "";
											Login.Login_password = "";
											class424.field3758 = 0;
											Login.otp = "";
										}

										while (var25.method1085()) {
											boolean var11 = false;

											for (var12 = 0; var12 < "1234567890".length(); ++var12) {
												if (var25.field1867 == "1234567890".charAt(var12)) {
													var11 = true;
													break;
												}
											}

											if (var25.field1869 == 13) {
												class328.method1678(0);
												Login.Login_username = "";
												Login.Login_password = "";
												class424.field3758 = 0;
												Login.otp = "";
											} else {
												if (var25.field1869 == 85 && Login.otp.length() > 0) {
													Login.otp = Login.otp.substring(0, Login.otp.length() - 1);
												}

												if (var25.field1869 == 84) {
													Login.otp.trim();
													if (Login.otp.length() != 6) {
														Actor.setLoginResponseString("", "Please enter a 6-digit PIN.", "");
														return;
													}

													class424.field3758 = Integer.parseInt(Login.otp);
													Login.otp = "";
													GrandExchangeOfferTotalQuantityComparator.method1911(true);
													Actor.setLoginResponseString("", "Connecting to server...", "");
													class129.updateGameState(20);
													return;
												}

												if (var11 && Login.otp.length() < 6) {
													Login.otp = Login.otp + var25.field1867;
												}
											}
										}
									} else {
										int var13;
										if (Login.loginIndex == 5) {
											var9 = Login.loginBoxX + 180 - 80;
											var10 = 321;
											if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
												World.method404();
												return;
											}

											var9 = Login.loginBoxX + 180 + 80;
											if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
												PlayerComposition.Login_promptCredentials(true);
											}

											var44 = 361;
											if (class313.field2821 != null) {
												var12 = class313.field2821.highX / 2;
												if (var5 == 1 && var41 >= class313.field2821.lowX - var12 && var41 <= var12 + class313.field2821.lowX && var42 >= var44 - 15 && var42 < var44) {
													NPC.openURL(UrlRequest.method661("secure", true) + "m=weblogin/g=oldscape/cant_log_in", true, false);
												}
											}

											while (var25.method1085()) {
												var46 = false;

												for (var13 = 0; var13 < "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"ï¿½$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".length(); ++var13) {
													if (var25.field1867 == "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"ï¿½$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".charAt(var13)) {
														var46 = true;
														break;
													}
												}

												if (var25.field1869 == 13) {
													PlayerComposition.Login_promptCredentials(true);
												} else {
													if (var25.field1869 == 85 && Login.Login_username.length() > 0) {
														Login.Login_username = Login.Login_username.substring(0, Login.Login_username.length() - 1);
													}

													if (var25.field1869 == 84) {
														World.method404();
														return;
													}

													if (var46 && Login.Login_username.length() < 320) {
														Login.Login_username = Login.Login_username + var25.field1867;
													}
												}
											}
										} else if (Login.loginIndex != 6) {
											if (Login.loginIndex == 7) {
												if (Message.field347 && !Client.onMobile) {
													var9 = Script.loginBoxCenter - 150;
													var45 = var9 + 40 + 240 + 25;
													var44 = 231;
													var12 = var44 + 40;
													if (var5 == 1 && var41 >= var9 && var41 <= var45 && var42 >= var44 && var42 <= var12) {
														Login.field748 = class152.method794(var9, var41);
													}

													var13 = Login.loginBoxX + 180 - 80;
													short var14 = 321;
													boolean var17;
													boolean var19;
													Date var30;
													boolean var32;
													java.util.Calendar var33;
													Date var34;
													if (var5 == 1 && var41 >= var13 - 75 && var41 <= var13 + 75 && var42 >= var14 - 20 && var42 <= var14 + 20) {
														label900: {
															try {
																var30 = class16.method53();
															} catch (ParseException var38) {
																class328.method1678(7);
																Actor.setLoginResponseString("Date not valid.", "Please ensure date follows the format", "DD/MM/YYYY and is after 01/01/1900");
																var32 = false;
																break label900;
															}

															if (var30 == null) {
																var32 = false;
															} else {
																var33 = java.util.Calendar.getInstance();
																var33.set(1, var33.get(1) - 13);
																var33.set(5, var33.get(5) + 1);
																var33.set(11, 0);
																var33.set(12, 0);
																var33.set(13, 0);
																var33.set(14, 0);
																var34 = var33.getTime();
																var17 = var30.before(var34);
																var19 = class453.method2295(var30);
																if (!var19) {
																	class328.method1678(7);
																	Actor.setLoginResponseString("Date not valid.", "Please ensure date follows the format", "DD/MM/YYYY and is after 01/01/1900");
																	var32 = false;
																} else {
																	if (!var17) {
																		World.field666 = 8388607;
																	} else {
																		World.field666 = (int)(var30.getTime() / 86400000L - 11745L);
																	}

																	var32 = true;
																}
															}
														}

														if (var32) {
															class129.updateGameState(50);
															return;
														}
													}

													var13 = Login.loginBoxX + 180 + 80;
													if (var5 == 1 && var41 >= var13 - 75 && var41 <= var13 + 75 && var42 >= var14 - 20 && var42 <= var14 + 20) {
														Login.field767 = new String[8];
														PlayerComposition.Login_promptCredentials(true);
													}

													while (var25.method1085()) {
														if (var25.field1869 == 101) {
															Login.field767[Login.field748] = null;
														}

														if (var25.field1869 == 85) {
															if (Login.field767[Login.field748] == null && Login.field748 > 0) {
																--Login.field748;
															}

															Login.field767[Login.field748] = null;
														}

														if (var25.field1867 >= '0' && var25.field1867 <= '9') {
															Login.field767[Login.field748] = "" + var25.field1867;
															if (Login.field748 < 7) {
																++Login.field748;
															}
														}

														if (var25.field1869 == 84) {
															label836: {
																try {
																	var30 = class16.method53();
																} catch (ParseException var37) {
																	class328.method1678(7);
																	Actor.setLoginResponseString("Date not valid.", "Please ensure date follows the format", "DD/MM/YYYY and is after 01/01/1900");
																	var32 = false;
																	break label836;
																}

																if (var30 == null) {
																	var32 = false;
																} else {
																	var33 = java.util.Calendar.getInstance();
																	var33.set(1, var33.get(1) - 13);
																	var33.set(5, var33.get(5) + 1);
																	var33.set(11, 0);
																	var33.set(12, 0);
																	var33.set(13, 0);
																	var33.set(14, 0);
																	var34 = var33.getTime();
																	var17 = var30.before(var34);
																	var19 = class453.method2295(var30);
																	if (!var19) {
																		class328.method1678(7);
																		Actor.setLoginResponseString("Date not valid.", "Please ensure date follows the format", "DD/MM/YYYY and is after 01/01/1900");
																		var32 = false;
																	} else {
																		if (!var17) {
																			World.field666 = 8388607;
																		} else {
																			World.field666 = (int)(var30.getTime() / 86400000L - 11745L);
																		}

																		var32 = true;
																	}
																}
															}

															if (var32) {
																class129.updateGameState(50);
															}

															return;
														}
													}
												} else {
													var9 = Login.loginBoxX + 180 - 80;
													var10 = 321;
													if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
														NPC.openURL(UrlRequest.method661("secure", true) + "m=dob/set_dob.ws", true, false);
														Actor.setLoginResponseString("", "Page has opened in the browser.", "");
														class328.method1678(6);
														return;
													}

													var9 = Login.loginBoxX + 180 + 80;
													if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
														PlayerComposition.Login_promptCredentials(true);
													}
												}
											} else if (Login.loginIndex == 8) {
												var9 = Login.loginBoxX + 180 - 80;
												var10 = 321;
												if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													NPC.openURL("https://www.jagex.com/terms/privacy", true, false);
													Actor.setLoginResponseString("", "Page has opened in the browser.", "");
													class328.method1678(6);
													return;
												}

												var9 = Login.loginBoxX + 180 + 80;
												if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													PlayerComposition.Login_promptCredentials(true);
												}
											} else if (Login.loginIndex == 9) {
												var9 = Login.loginBoxX + 180;
												var10 = 311;
												if (var25.field1869 == 84 || var25.field1869 == 13 || var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													class333.method1811(false);
												}
											} else if (Login.loginIndex == 10) {
												var9 = Login.loginBoxX + 180;
												var10 = 209;
												if (var25.field1869 == 84 || var5 == 1 && var41 >= var9 - 109 && var41 <= var9 + 109 && var42 >= var10 && var42 <= var10 + 68) {
													Actor.setLoginResponseString("", "Connecting to server...", "");
													Client.field644 = class518.field4160;
													GrandExchangeOfferTotalQuantityComparator.method1911(false);
													class129.updateGameState(20);
												}
											} else if (Login.loginIndex == 12) {
												var9 = Script.loginBoxCenter;
												var10 = 233;
												var29 = var2.method2048(0, 30, "<col=ffd200>terms of use</col>, <col=ffd200>privacy policy</col>, and <col=ffd200>end user licence</col>", var9, var10);
												Bounds var26 = var2.method2048(32, 32, "<col=ffd200>terms of use</col>, <col=ffd200>privacy policy</col>, and <col=ffd200>end user licence</col>", var9, var10);
												Bounds var27 = var2.method2048(70, 34, "<col=ffd200>terms of use</col>, <col=ffd200>privacy policy</col>, and <col=ffd200>end user licence</col>", var9, var10);
												var45 = var10 + 17;
												Bounds var35 = var2.method2048(0, 34, "<col=ffd200>agreement (EULA)</col>.", var9, var45);
												if (var5 == 1) {
													if (var29.method2257(var41, var42)) {
														NPC.openURL("https://www.jagex.com/terms", true, false);
													} else if (var26.method2257(var41, var42)) {
														NPC.openURL("https://www.jagex.com/terms/privacy", true, false);
													} else if (var27.method2257(var41, var42) || var35.method2257(var41, var42)) {
														NPC.openURL("https://www.jagex.com/en-GB/legal/eula-runescape-oldschool", true, false);
													}
												}

												var9 = Script.loginBoxCenter - 80;
												var10 = 311;
												if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													class150.clientPreferences.method570(Client.param21);
													class333.method1811(true);
												}

												var9 = Script.loginBoxCenter + 80;
												if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													Login.loginIndex = 13;
												}
											} else if (Login.loginIndex == 13) {
												var9 = Script.loginBoxCenter;
												var10 = 321;
												if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													class333.method1811(true);
												}
											} else if (Login.loginIndex == 14) {
												String var36 = "";
												switch(Login.field746) {
												case 0:
													var36 = "https://secure.runescape.com/m=offence-appeal/account-history";
													break;
												case 1:
													var36 = "https://secure.runescape.com/m=accountappeal/passwordrecovery";
													break;
												case 2:
													var36 = "https://support.runescape.com/hc/en-gb/articles/207256855-Settle-an-Unpaid-Balance";
													break;
												default:
													PlayerComposition.Login_promptCredentials(false);
												}

												var45 = Login.loginBoxX + 180;
												var44 = 276;
												if (var5 == 1 && var41 >= var45 - 75 && var41 <= var45 + 75 && var42 >= var44 - 20 && var42 <= var44 + 20) {
													NPC.openURL(var36, true, false);
													Actor.setLoginResponseString("", "Page has opened in the browser.", "");
													class328.method1678(6);
													return;
												}

												var45 = Login.loginBoxX + 180;
												var44 = 326;
												if (var5 == 1 && var41 >= var45 - 75 && var41 <= var45 + 75 && var42 >= var44 - 20 && var42 <= var44 + 20) {
													PlayerComposition.Login_promptCredentials(false);
												}
											} else if (Login.loginIndex == 24) {
												var9 = Login.loginBoxX + 180;
												var10 = 301;
												if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													class333.method1811(false);
												}
											} else if (Login.loginIndex == 32) {
												var9 = Login.loginBoxX + 180 - 80;
												var10 = 321;
												if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													NPC.openURL(UrlRequest.method661("secure", true) + "m=dob/set_dob.ws", true, false);
													Actor.setLoginResponseString("", "Page has opened in the browser.", "");
													class328.method1678(6);
													return;
												}

												var9 = Login.loginBoxX + 180 + 80;
												if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													PlayerComposition.Login_promptCredentials(true);
												}
											} else if (Login.loginIndex == 33) {
												var9 = Login.loginBoxX + 180;
												var10 = 276;
												if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													NPC.openURL("https://oldschool.runescape.com/launcher", true, false);
												}

												var9 = Login.loginBoxX + 180;
												var10 = 326;
												if (var5 == 1 && var41 >= var9 - 75 && var41 <= var9 + 75 && var42 >= var10 - 20 && var42 <= var10 + 20) {
													PlayerComposition.Login_promptCredentials(true);
												}
											}
										} else {
											while (true) {
												do {
													if (!var25.method1085()) {
														var43 = 321;
														if (var5 == 1 && var42 >= var43 - 20 && var42 <= var43 + 20) {
															PlayerComposition.Login_promptCredentials(true);
														}

														return;
													}
												} while(var25.field1869 != 84 && var25.field1869 != 13);

												PlayerComposition.Login_promptCredentials(true);
											}
										}
									}
								}
							}
						}
					}

				}
			}
		}
	}

	@ObfuscatedName("av")
	@ObfuscatedSignature(
		descriptor = "(B)Lud;",
		garbageValue = "-79"
	)
	static SpritePixels method905() {
		SpritePixels var0 = new SpritePixels();
		var0.width = class528.SpriteBuffer_spriteWidth;
		var0.height = class528.SpriteBuffer_spriteHeight;
		var0.xOffset = class492.SpriteBuffer_xOffsets[0];
		var0.yOffset = class134.SpriteBuffer_yOffsets[0];
		var0.subWidth = class172.SpriteBuffer_spriteWidths[0];
		var0.subHeight = class528.SpriteBuffer_spriteHeights[0];
		int var1 = var0.subHeight * var0.subWidth;
		byte[] var2 = ArchiveDiskAction.SpriteBuffer_pixels[0];
		var0.pixels = new int[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var0.pixels[var3] = class528.SpriteBuffer_spritePalette[var2[var3] & 255];
		}

		DbTableType.method2483();
		return var0;
	}
}
