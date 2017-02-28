INSERT INTO SOAPBOX.USER (ID, EMAIL, PASSWORD) VALUES (1, 'dev@nilzao', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3');
INSERT INTO SOAPBOX.USER (ID, EMAIL, PASSWORD) VALUES (2, 'dev@berkay', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3');
INSERT INTO SOAPBOX.USER (ID, EMAIL, PASSWORD) VALUES (3, 'debug@player1', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3');
INSERT INTO SOAPBOX.USER (ID, EMAIL, PASSWORD) VALUES (4, 'debug@player2', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3');
INSERT INTO SOAPBOX.USER (ID, EMAIL, PASSWORD) VALUES (5, 'debug@player3', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3');
INSERT INTO SOAPBOX.USER (ID, EMAIL, PASSWORD) VALUES (6, 'dev@leorblx', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3');
INSERT INTO SOAPBOX.PERSONA (ID, BADGES, CASH, CURCARINDEX, ICONINDEX, LEVEL, MOTTO, NAME, PERCENTTOLEVEL, RATING, REP, REPATCURRENTLEVEL, SCORE, USERID)
VALUES (100, '', 5000000, 0, 1, 60, '', 'DEVNilzao', 3, 0, 5, 5, 5, 1);
INSERT INTO SOAPBOX.PERSONA (ID, BADGES, CASH, CURCARINDEX, ICONINDEX, LEVEL, MOTTO, NAME, PERCENTTOLEVEL, RATING, REP, REPATCURRENTLEVEL, SCORE, USERID)
VALUES (101, '', 5000000, 0, 2, 60, '', 'DEVberkay', 3, 0, 5, 5, 5, 2);
INSERT INTO SOAPBOX.PERSONA (ID, BADGES, CASH, CURCARINDEX, ICONINDEX, LEVEL, MOTTO, NAME, PERCENTTOLEVEL, RATING, REP, REPATCURRENTLEVEL, SCORE, USERID)
VALUES (102, '', 5000000, 0, 3, 60, '', 'DEBUG1', 3, 0, 5, 5, 5, 3);
INSERT INTO SOAPBOX.PERSONA (ID, BADGES, CASH, CURCARINDEX, ICONINDEX, LEVEL, MOTTO, NAME, PERCENTTOLEVEL, RATING, REP, REPATCURRENTLEVEL, SCORE, USERID)
VALUES (103, '', 5000000, 0, 4, 60, '', 'DEBUG2', 3, 0, 5, 5, 5, 4);
INSERT INTO SOAPBOX.PERSONA (ID, BADGES, CASH, CURCARINDEX, ICONINDEX, LEVEL, MOTTO, NAME, PERCENTTOLEVEL, RATING, REP, REPATCURRENTLEVEL, SCORE, USERID)
VALUES (104, '', 5000000, 0, 5, 60, '', 'DEBUG3', 3, 0, 5, 5, 5, 5);
INSERT INTO SOAPBOX.PERSONA (ID, BADGES, CASH, CURCARINDEX, ICONINDEX, LEVEL, MOTTO, NAME, PERCENTTOLEVEL, RATING, REP, REPATCURRENTLEVEL, SCORE, USERID)
VALUES (105, '', 5000000, 0, 2, 60, '', 'DEVleorblx', 3, 0, 5, 5, 5, 2);
INSERT INTO SOAPBOX.OWNEDCAR (Durability, ExpirationDate, HeatLevel, UniqueCarId, OwnershipType, PersonaId)
VALUES (100, NULL, 1, 1, 'CustomizedCar', 100);
INSERT INTO SOAPBOX.OWNEDCAR (Durability, ExpirationDate, HeatLevel, UniqueCarId, OwnershipType, PersonaId)
VALUES (100, NULL, 1, 2, 'CustomizedCar', 101);
INSERT INTO SOAPBOX.OWNEDCAR (Durability, ExpirationDate, HeatLevel, UniqueCarId, OwnershipType, PersonaId)
VALUES (100, NULL, 1, 3, 'CustomizedCar', 102);
INSERT INTO SOAPBOX.OWNEDCAR (Durability, ExpirationDate, HeatLevel, UniqueCarId, OwnershipType, PersonaId)
VALUES (100, NULL, 1, 4, 'CustomizedCar', 103);
INSERT INTO SOAPBOX.OWNEDCAR (Durability, ExpirationDate, HeatLevel, UniqueCarId, OwnershipType, PersonaId)
VALUES (100, NULL, 1, 5, 'CustomizedCar', 104);
INSERT INTO SOAPBOX.OWNEDCAR (Durability, ExpirationDate, HeatLevel, UniqueCarId, OwnershipType, PersonaId)
VALUES (100, NULL, 1, 6, 'CustomizedCar', 105);
INSERT INTO SOAPBOX.CUSTOMCAR (BaseCarId, CarClassHash, IsPreset, Level, Name, ApiId, Paints, PerformanceParts, PhysicsProfileHash, Rating, ResalePrice, SkillModParts, SkillModSlotCount, Vinyls, VisualParts, IdParentOwnedCarTrans)
VALUES (1816139026, -405837480, FALSE, 0, NULL, 9,
                    '<Paints><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>1</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>2</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>6</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>0</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>3</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>4</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>5</Slot><Var>254</Var></CustomPaintTrans></Paints>',
                    '<PerformanceParts><PerformancePartTrans><PerformancePartAttribHash>-1962598619</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>-183076819</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>7155944</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>754340312</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1621862030</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1727386028</PerformancePartAttribHash></PerformancePartTrans></PerformanceParts>',
                    -846723009, 708, 350000,
        '<SkillModParts><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1196331958</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1012293684</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-577002039</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>861531645</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>917249206</SkillModPartAttribHash></SkillModPartTrans></SkillModParts>',
        5,
        '<Vinyls><CustomVinylTrans><Hash>-883491363</Hash><Hue1>-799662319</Hue1><Hue2>-799662186</Hue2><Hue3>-799662452</Hue3><Hue4>-799662452</Hue4><Layer>0</Layer><Mir>true</Mir><Rot>128</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>7162</ScaleX><ScaleY>11595</ScaleY><Shear>0</Shear><TranX>2</TranX><TranY>327</TranY><Var1>204</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans><CustomVinylTrans><Hash>-1282944374</Hash><Hue1>-799662156</Hue1><Hue2>-799662354</Hue2><Hue3>-799662385</Hue3><Hue4>-799662385</Hue4><Layer>1</Layer><Mir>true</Mir><Rot>60</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>735</ScaleX><ScaleY>1063</ScaleY><Shear>0</Shear><TranX>-52</TranX><TranY>268</TranY><Var1>255</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans></Vinyls>',
        '<VisualParts><VisualPartTrans><PartHash>-541305606</PartHash><SlotHash>1694991</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-273819714</PartHash><SlotHash>-2126743923</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-48607787</PartHash><SlotHash>453545749</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>948331475</PartHash><SlotHash>2106784967</SlotHash></VisualPartTrans></VisualParts>',
        1);
INSERT INTO SOAPBOX.CUSTOMCAR (BaseCarId, CarClassHash, IsPreset, Level, Name, ApiId, Paints, PerformanceParts, PhysicsProfileHash, Rating, ResalePrice, SkillModParts, SkillModSlotCount, Vinyls, VisualParts, IdParentOwnedCarTrans)
VALUES (1816139026, -405837480, FALSE, 0, NULL, 9,
                    '<Paints><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>1</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>2</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>6</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>0</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>3</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>4</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>5</Slot><Var>254</Var></CustomPaintTrans></Paints>',
                    '<PerformanceParts><PerformancePartTrans><PerformancePartAttribHash>-1962598619</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>-183076819</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>7155944</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>754340312</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1621862030</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1727386028</PerformancePartAttribHash></PerformancePartTrans></PerformanceParts>',
                    -846723009, 708, 350000,
        '<SkillModParts><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1196331958</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1012293684</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-577002039</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>861531645</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>917249206</SkillModPartAttribHash></SkillModPartTrans></SkillModParts>',
        5,
        '<Vinyls><CustomVinylTrans><Hash>-883491363</Hash><Hue1>-799662319</Hue1><Hue2>-799662186</Hue2><Hue3>-799662452</Hue3><Hue4>-799662452</Hue4><Layer>0</Layer><Mir>true</Mir><Rot>128</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>7162</ScaleX><ScaleY>11595</ScaleY><Shear>0</Shear><TranX>2</TranX><TranY>327</TranY><Var1>204</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans><CustomVinylTrans><Hash>-1282944374</Hash><Hue1>-799662156</Hue1><Hue2>-799662354</Hue2><Hue3>-799662385</Hue3><Hue4>-799662385</Hue4><Layer>1</Layer><Mir>true</Mir><Rot>60</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>735</ScaleX><ScaleY>1063</ScaleY><Shear>0</Shear><TranX>-52</TranX><TranY>268</TranY><Var1>255</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans></Vinyls>',
        '<VisualParts><VisualPartTrans><PartHash>-541305606</PartHash><SlotHash>1694991</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-273819714</PartHash><SlotHash>-2126743923</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-48607787</PartHash><SlotHash>453545749</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>948331475</PartHash><SlotHash>2106784967</SlotHash></VisualPartTrans></VisualParts>',
        2);
INSERT INTO SOAPBOX.CUSTOMCAR (BaseCarId, CarClassHash, IsPreset, Level, Name, ApiId, Paints, PerformanceParts, PhysicsProfileHash, Rating, ResalePrice, SkillModParts, SkillModSlotCount, Vinyls, VisualParts, IdParentOwnedCarTrans)
VALUES (1816139026, -405837480, FALSE, 0, NULL, 9,
                    '<Paints><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>1</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>2</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>6</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>0</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>3</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>4</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>5</Slot><Var>254</Var></CustomPaintTrans></Paints>',
                    '<PerformanceParts><PerformancePartTrans><PerformancePartAttribHash>-1962598619</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>-183076819</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>7155944</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>754340312</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1621862030</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1727386028</PerformancePartAttribHash></PerformancePartTrans></PerformanceParts>',
                    -846723009, 708, 350000,
        '<SkillModParts><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1196331958</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1012293684</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-577002039</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>861531645</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>917249206</SkillModPartAttribHash></SkillModPartTrans></SkillModParts>',
        5,
        '<Vinyls><CustomVinylTrans><Hash>-883491363</Hash><Hue1>-799662319</Hue1><Hue2>-799662186</Hue2><Hue3>-799662452</Hue3><Hue4>-799662452</Hue4><Layer>0</Layer><Mir>true</Mir><Rot>128</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>7162</ScaleX><ScaleY>11595</ScaleY><Shear>0</Shear><TranX>2</TranX><TranY>327</TranY><Var1>204</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans><CustomVinylTrans><Hash>-1282944374</Hash><Hue1>-799662156</Hue1><Hue2>-799662354</Hue2><Hue3>-799662385</Hue3><Hue4>-799662385</Hue4><Layer>1</Layer><Mir>true</Mir><Rot>60</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>735</ScaleX><ScaleY>1063</ScaleY><Shear>0</Shear><TranX>-52</TranX><TranY>268</TranY><Var1>255</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans></Vinyls>',
        '<VisualParts><VisualPartTrans><PartHash>-541305606</PartHash><SlotHash>1694991</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-273819714</PartHash><SlotHash>-2126743923</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-48607787</PartHash><SlotHash>453545749</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>948331475</PartHash><SlotHash>2106784967</SlotHash></VisualPartTrans></VisualParts>',
        3);
INSERT INTO SOAPBOX.CUSTOMCAR (BaseCarId, CarClassHash, IsPreset, Level, Name, ApiId, Paints, PerformanceParts, PhysicsProfileHash, Rating, ResalePrice, SkillModParts, SkillModSlotCount, Vinyls, VisualParts, IdParentOwnedCarTrans)
VALUES (1816139026, -405837480, FALSE, 0, NULL, 9,
                    '<Paints><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>1</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>2</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>6</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>0</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>3</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>4</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>5</Slot><Var>254</Var></CustomPaintTrans></Paints>',
                    '<PerformanceParts><PerformancePartTrans><PerformancePartAttribHash>-1962598619</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>-183076819</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>7155944</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>754340312</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1621862030</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1727386028</PerformancePartAttribHash></PerformancePartTrans></PerformanceParts>',
                    -846723009, 708, 350000,
        '<SkillModParts><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1196331958</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1012293684</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-577002039</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>861531645</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>917249206</SkillModPartAttribHash></SkillModPartTrans></SkillModParts>',
        5,
        '<Vinyls><CustomVinylTrans><Hash>-883491363</Hash><Hue1>-799662319</Hue1><Hue2>-799662186</Hue2><Hue3>-799662452</Hue3><Hue4>-799662452</Hue4><Layer>0</Layer><Mir>true</Mir><Rot>128</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>7162</ScaleX><ScaleY>11595</ScaleY><Shear>0</Shear><TranX>2</TranX><TranY>327</TranY><Var1>204</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans><CustomVinylTrans><Hash>-1282944374</Hash><Hue1>-799662156</Hue1><Hue2>-799662354</Hue2><Hue3>-799662385</Hue3><Hue4>-799662385</Hue4><Layer>1</Layer><Mir>true</Mir><Rot>60</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>735</ScaleX><ScaleY>1063</ScaleY><Shear>0</Shear><TranX>-52</TranX><TranY>268</TranY><Var1>255</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans></Vinyls>',
        '<VisualParts><VisualPartTrans><PartHash>-541305606</PartHash><SlotHash>1694991</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-273819714</PartHash><SlotHash>-2126743923</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-48607787</PartHash><SlotHash>453545749</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>948331475</PartHash><SlotHash>2106784967</SlotHash></VisualPartTrans></VisualParts>',
        4);
INSERT INTO SOAPBOX.CUSTOMCAR (BaseCarId, CarClassHash, IsPreset, Level, Name, ApiId, Paints, PerformanceParts, PhysicsProfileHash, Rating, ResalePrice, SkillModParts, SkillModSlotCount, Vinyls, VisualParts, IdParentOwnedCarTrans)
VALUES (1816139026, -405837480, FALSE, 0, NULL, 9,
                    '<Paints><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>1</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>2</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>6</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>0</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>3</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>4</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>5</Slot><Var>254</Var></CustomPaintTrans></Paints>',
                    '<PerformanceParts><PerformancePartTrans><PerformancePartAttribHash>-1962598619</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>-183076819</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>7155944</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>754340312</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1621862030</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1727386028</PerformancePartAttribHash></PerformancePartTrans></PerformanceParts>',
                    -846723009, 708, 350000,
        '<SkillModParts><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1196331958</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1012293684</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-577002039</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>861531645</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>917249206</SkillModPartAttribHash></SkillModPartTrans></SkillModParts>',
        5,
        '<Vinyls><CustomVinylTrans><Hash>-883491363</Hash><Hue1>-799662319</Hue1><Hue2>-799662186</Hue2><Hue3>-799662452</Hue3><Hue4>-799662452</Hue4><Layer>0</Layer><Mir>true</Mir><Rot>128</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>7162</ScaleX><ScaleY>11595</ScaleY><Shear>0</Shear><TranX>2</TranX><TranY>327</TranY><Var1>204</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans><CustomVinylTrans><Hash>-1282944374</Hash><Hue1>-799662156</Hue1><Hue2>-799662354</Hue2><Hue3>-799662385</Hue3><Hue4>-799662385</Hue4><Layer>1</Layer><Mir>true</Mir><Rot>60</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>735</ScaleX><ScaleY>1063</ScaleY><Shear>0</Shear><TranX>-52</TranX><TranY>268</TranY><Var1>255</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans></Vinyls>',
        '<VisualParts><VisualPartTrans><PartHash>-541305606</PartHash><SlotHash>1694991</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-273819714</PartHash><SlotHash>-2126743923</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-48607787</PartHash><SlotHash>453545749</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>948331475</PartHash><SlotHash>2106784967</SlotHash></VisualPartTrans></VisualParts>',
        5);
INSERT INTO SOAPBOX.CUSTOMCAR (BaseCarId, CarClassHash, IsPreset, Level, Name, ApiId, Paints, PerformanceParts, PhysicsProfileHash, Rating, ResalePrice, SkillModParts, SkillModSlotCount, Vinyls, VisualParts, IdParentOwnedCarTrans)
VALUES (1816139026, -405837480, FALSE, 0, NULL, 9,
                    '<Paints><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>1</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>-1480403439</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>2</Slot><Var>76</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>6</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>0</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>3</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>4</Slot><Var>254</Var></CustomPaintTrans><CustomPaintTrans><Group>595033610</Group><Hue>496032624</Hue><Sat>0</Sat><Slot>5</Slot><Var>254</Var></CustomPaintTrans></Paints>',
                    '<PerformanceParts><PerformancePartTrans><PerformancePartAttribHash>-1962598619</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>-183076819</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>7155944</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>754340312</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1621862030</PerformancePartAttribHash></PerformancePartTrans><PerformancePartTrans><PerformancePartAttribHash>1727386028</PerformancePartAttribHash></PerformancePartTrans></PerformanceParts>',
                    -846723009, 708, 350000,
        '<SkillModParts><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1196331958</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-1012293684</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>-577002039</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>861531645</SkillModPartAttribHash></SkillModPartTrans><SkillModPartTrans><IsFixed>false</IsFixed><SkillModPartAttribHash>917249206</SkillModPartAttribHash></SkillModPartTrans></SkillModParts>',
        5,
        '<Vinyls><CustomVinylTrans><Hash>-883491363</Hash><Hue1>-799662319</Hue1><Hue2>-799662186</Hue2><Hue3>-799662452</Hue3><Hue4>-799662452</Hue4><Layer>0</Layer><Mir>true</Mir><Rot>128</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>7162</ScaleX><ScaleY>11595</ScaleY><Shear>0</Shear><TranX>2</TranX><TranY>327</TranY><Var1>204</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans><CustomVinylTrans><Hash>-1282944374</Hash><Hue1>-799662156</Hue1><Hue2>-799662354</Hue2><Hue3>-799662385</Hue3><Hue4>-799662385</Hue4><Layer>1</Layer><Mir>true</Mir><Rot>60</Rot><Sat1>0</Sat1><Sat2>0</Sat2><Sat3>0</Sat3><Sat4>0</Sat4><ScaleX>735</ScaleX><ScaleY>1063</ScaleY><Shear>0</Shear><TranX>-52</TranX><TranY>268</TranY><Var1>255</Var1><Var2>0</Var2><Var3>0</Var3><Var4>0</Var4></CustomVinylTrans></Vinyls>',
        '<VisualParts><VisualPartTrans><PartHash>-541305606</PartHash><SlotHash>1694991</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-273819714</PartHash><SlotHash>-2126743923</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>-48607787</PartHash><SlotHash>453545749</SlotHash></VisualPartTrans><VisualPartTrans><PartHash>948331475</PartHash><SlotHash>2106784967</SlotHash></VisualPartTrans></VisualParts>',
        6);

INSERT INTO SOAPBOX.PERSONAINVENTORY (personaId) VALUES (100);
INSERT INTO SOAPBOX.PERSONAINVENTORY (personaId) VALUES (101);
INSERT INTO SOAPBOX.PERSONAINVENTORY (personaId) VALUES (102);
INSERT INTO SOAPBOX.PERSONAINVENTORY (personaId) VALUES (103);
INSERT INTO SOAPBOX.PERSONAINVENTORY (personaId) VALUES (104);
INSERT INTO SOAPBOX.PERSONAINVENTORY (personaId) VALUES (105);

# Persona 100
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
    1, 100, 'nosshot', NULL, -1681514783, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9bc61ee1', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'runflattires', NULL, -537557654, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdff5856a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'instantcooldown', NULL, -1692359144, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9b20a618', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'shield', NULL, -364944936, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xea3f61d8', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'slingshot', NULL, 2236629, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x2220d5', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'ready', NULL, 957701799, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x39155ea7', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'juggernaut', NULL, 1805681994, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x6ba0854a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'emergencyevade', NULL, -611661916, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdb8ac7a4', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'team_emergencyevade', NULL, -1564932069, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xa2b9081b', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'onemorelap', NULL, 1627606782, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x61034efe', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'team_slingshot', NULL, 1113720384, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x42620640', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  1, 100, 'trafficmagnet', NULL, 125509666, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x77b2022', 'powerup'
);

# Persona 101
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'nosshot', NULL, -1681514783, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9bc61ee1', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'runflattires', NULL, -537557654, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdff5856a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'instantcooldown', NULL, -1692359144, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9b20a618', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'shield', NULL, -364944936, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xea3f61d8', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'slingshot', NULL, 2236629, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x2220d5', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'ready', NULL, 957701799, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x39155ea7', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'juggernaut', NULL, 1805681994, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x6ba0854a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'emergencyevade', NULL, -611661916, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdb8ac7a4', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'team_emergencyevade', NULL, -1564932069, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xa2b9081b', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'onemorelap', NULL, 1627606782, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x61034efe', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'team_slingshot', NULL, 1113720384, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x42620640', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  2, 101, 'trafficmagnet', NULL, 125509666, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x77b2022', 'powerup'
);

# Persona 102
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'nosshot', NULL, -1681514783, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9bc61ee1', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'runflattires', NULL, -537557654, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdff5856a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'instantcooldown', NULL, -1692359144, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9b20a618', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'shield', NULL, -364944936, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xea3f61d8', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'slingshot', NULL, 2236629, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x2220d5', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'ready', NULL, 957701799, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x39155ea7', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'juggernaut', NULL, 1805681994, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x6ba0854a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'emergencyevade', NULL, -611661916, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdb8ac7a4', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'team_emergencyevade', NULL, -1564932069, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xa2b9081b', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'onemorelap', NULL, 1627606782, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x61034efe', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'team_slingshot', NULL, 1113720384, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x42620640', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  3, 102, 'trafficmagnet', NULL, 125509666, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x77b2022', 'powerup'
);

# Persona 103
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'nosshot', NULL, -1681514783, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9bc61ee1', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'runflattires', NULL, -537557654, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdff5856a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'instantcooldown', NULL, -1692359144, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9b20a618', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'shield', NULL, -364944936, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xea3f61d8', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'slingshot', NULL, 2236629, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x2220d5', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'ready', NULL, 957701799, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x39155ea7', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'juggernaut', NULL, 1805681994, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x6ba0854a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'emergencyevade', NULL, -611661916, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdb8ac7a4', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'team_emergencyevade', NULL, -1564932069, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xa2b9081b', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'onemorelap', NULL, 1627606782, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x61034efe', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'team_slingshot', NULL, 1113720384, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x42620640', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  4, 103, 'trafficmagnet', NULL, 125509666, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x77b2022', 'powerup'
);

# Persona 104
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'nosshot', NULL, -1681514783, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9bc61ee1', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'runflattires', NULL, -537557654, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdff5856a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'instantcooldown', NULL, -1692359144, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9b20a618', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'shield', NULL, -364944936, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xea3f61d8', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'slingshot', NULL, 2236629, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x2220d5', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'ready', NULL, 957701799, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x39155ea7', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'juggernaut', NULL, 1805681994, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x6ba0854a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'emergencyevade', NULL, -611661916, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdb8ac7a4', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'team_emergencyevade', NULL, -1564932069, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xa2b9081b', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'onemorelap', NULL, 1627606782, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x61034efe', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'team_slingshot', NULL, 1113720384, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x42620640', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  5, 104, 'trafficmagnet', NULL, 125509666, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x77b2022', 'powerup'
);

# Persona 105
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'nosshot', NULL, -1681514783, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9bc61ee1', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'runflattires', NULL, -537557654, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdff5856a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'instantcooldown', NULL, -1692359144, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x9b20a618', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'shield', NULL, -364944936, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xea3f61d8', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'slingshot', NULL, 2236629, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x2220d5', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'ready', NULL, 957701799, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x39155ea7', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'juggernaut', NULL, 1805681994, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x6ba0854a', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'emergencyevade', NULL, -611661916, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xdb8ac7a4', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'team_emergencyevade', NULL, -1564932069, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0xa2b9081b', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'onemorelap', NULL, 1627606782, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x61034efe', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'team_slingshot', NULL, 1113720384, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x42620640', 'powerup'
);
INSERT INTO SOAPBOX.PERSONAINVENTORYITEM
(inventoryId, personaId, entitlementTag, expirationDate, hash, productId, remainingUseCount, resalePrice, status, stringHash, virtualItemType)
VALUES (
  6, 105, 'trafficmagnet', NULL, 125509666, 'DO NOT USE ME', 500, 0.00000, 'ACTIVE', '0x77b2022', 'powerup'
);