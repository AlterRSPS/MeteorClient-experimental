package cloud;

public enum ServerPacketsEnum {
    NPC_ANIM_SPECIFIC(0, 5),
    AREA_SOUND(1, 5),
    TRADING_POST_UPDATE(2, 20),
    SEND_PING(3, 8),
    UPDATE_REBOOT_TIMER(4, 2),
    IF_SETEVENTS(5, 12),
    NEW_PACKET_CAM_RESET_V2(6, 4),
    SET_PLAYER_OP(7, -1),
    VARP_LARGE(8, 6),
    IF_SETOBJECT(9, 10),
    OPEN_URL(10, -2),
    IF_MOVESUB(11, 8),
    CAM_LOOKAT_EASED_ANGLE_ABSOLUTE(12, 7),
    CAM_MOVETO_EASED_CIRCULAR(13, 10),
    IF_SETTEXT(14, -2),
    REFLECTIONCHECK_FRIENDLIST(15, -1),
    MAPPROJ_ANIM(16, 16),
    GAMEFRAME_FULL(17, -2),
    UPDATE_INV_STOP_TRANSMIT(18, 2),
    MESSAGE_PRIVATE_ECHO(19, -2),
    RUN_CLIENTSCRIPT(20, -2),
    VARCLAN_ENABLE(21, 0),
    MAPPROJ_ANIM_SPECIFIC(22, 17),
    UPDATE_RUNENERGY(23, 2),
    CAM_SHAKE(24, 4),
    MESSAGE_CLANCHANNEL(25, -1),
    IF_SETNPCHEAD(26, 6),
    IF_CLOSESUB(27, 4),
    UPDATE_STAT(28, 6),
    UPDATE_IGNORELIST(29, -2),
    IF_SETCOLOUR(30, 6),
    MAP_ANIM(31, 6),
    NPC_SPOTANIM_SPECIFIC(32, 9),
    HEAT_MAP(33, 1),
    LOC_ADD_CHANGE(34, 5),
    PLAYER_ANIM_SPECIFIC(35, 3),
    OBJ_ADD(36, 14),
    TRADING_POST_RESULTS(37, -2),
    SPOTANIM_SPECIFIC(38, 8),
    VARP_SMALL(39, 3),
    CHAT_FILTER_SETTINGS(40, 2),
    UPDATE_RUNWEIGHT(41, 2),
    UPDATE_ZONE_PARTIAL_FOLLOWS(42, 2),
    RESET_ANIMS(43, 0),
    SYNTH_SOUND(44, 5),
    MIDI_JINGLE(45, 5),
    IF1_MODELROTATE(46, 8),
    HINT_ARROW(47, 6),
    NEW_PACK_215(48, -1),
    IF_SETPLAYERMODEL_BASECOLOUR(49, 6),
    IF_SETANGLE(50, 10),
    NPC_HEADICON_SPECIFIC(51, 9),
    MESSAGE_PRIVATE(52, -2),
    IF_SETSCROLLPOS(53, 6),
    REBUILD_REGION(54, -2),
    LOC_ANIM_SPECIFIC(55, 6),
    MESSAGE_GAME(56, -1),
    UPDATE_INV_FULL(57, -2),
    IF_SETANIM(58, 6),
    IF_OPENSUB(59, 7),
    IF_SETMODEL(60, 6),
    UPDATE_UID192(61, 28),
    SYNC_CLIENT_VARCACHE(62, 0),
    CLANSETTINGS_FULL(63, -2),
    CAM_LOOKAT(64, 6),
    SET_MAP_FLAG(65, 2),
    MINIMAP_TOGGLE(66, 1),
    UPDATE_FRIENDLIST(67, -2),
    TRIGGER_ONDIALOGABORT(68, 0),
    IF_SETPLAYERMODEL_OBJ(69, 8),
    PLAYER_SPOTANIM_SPECIFIC(70, 9),
    OBJ_COUNT(71, 11),
    IF_SETPOSITION(72, 8),
    UPDATE_INV_PARTIAL(73, -2),
    RESET_CLIENT_VARCACHE(74, 0),
    FRIENDLIST_LOADED(75, 0),
    NPC_INFO_SMALL(76, -2),
    MESSAGE_FRIENDCHANNEL(77, -1),
    CHAT_FILTER_SETTINGS_PRIVATECHAT(78, 1),
    PLAYER_INFO(79, -2),
    IF_SETPLAYERMODEL_BODYTYPE(80, 5),
    TOGGLE_OCULUS_ORB(81, 4),
    CAM_MOVETO_EASED(82, 8),
    VARCLAN(83, -1),
    LOC_DEL(84, 2),
    REFLECTION_CHECK(85, -2),
    VARCLAN_DISABLE(86, 0),
    ENTER_FREECAM(87, 1),
    CLANCHANNEL_FULL(88, -2),
    MIDI_SONG_LEGACY(89, 2),
    IF_SETHIDE(90, 5),
    MESSAGE_CLANCHANNEL_SYSTEM(91, -1),
    OBJ_DEL_LEGACY(92, 4),
    CLANSETTINGS_DELTA(93, -2),
    UPDATE_ZONE_FULL_FOLLOWS(94, 2),
    UPDATE_INV_CLEAR(95, 4),
    CLANCHANNEL_DELTA(96, -2),
    CAM_RESET(97, 0),
    EMPTY_PACKET(98, 0),
    REBUILD_REGION_NORMAL(99, -2),
    LOGOUT(100, 1),
    LOGOUT_FULL(101, 0),
    UPDATE_ZONE_PARTIAL_ENCLOSED(102, -2),
    CAM_LOOKAT_EASED_COORD(103, 7),
    CAM_LOOKAT_EASED_ANGLE_RELATIVE(104, 7),
    UPDATE_FRIENDCHAT_CHANNEL_FULL_V2(105, -2),
    IF_OPENTOP(106, 2),
    field2719(107, -2),
    CAM_MOVETO(108, 6),
    UPDATE_SITESETTINGS(109, -1),
    PREFETCH_GAMEOBJECTS(110, 14),
    IF_SETPLAYERHEAD(111, 4),
    LOC_ANIM(112, 4),
    OBJ_DEL(113, 7),
    LOGIN_SCREEN_PACKET(114, 6),
    NPC_INFO_LARGE(115, -2),
    IF_SETPLAYERMODEL_SELF(116, 5),
    UPDATE_FRIENDCHAT_CHANNEL_FULL(117, -2),
    MIDI_SONG(118, 10),
    MIDI_SONG_STOP(119, 4),
    MIDI_SONG_WITH_SECONDARY(120, 12),
    MIDI_SWAP(121, 8);

    private final int id;
    private final int length;

    ServerPacketsEnum(int id, int length) {
        this.id = id;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return length;
    }

    public static ServerPacketsEnum getById(int id) {
        for (ServerPacketsEnum  e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }
}