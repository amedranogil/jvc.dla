package es.upm.tfo.lst.jvc.dla;

/**
 * 
 * source http://www.projectorcentral.com/pdf/projector_manual_7197.pdf
 * @author amedrano
 *
 */
public interface Binary {
	
	public int PORT = 20554;
	
	//AuthProtocol
	public String PJOK = "PJ_OK";
	public String PJREQ = "PJREQ";
	public String PJACK = "PJACK";
	public String PJ_NG = "PJ_NG";
	public String PJ_NAK = "PJNAK";
	
	// Headers
	public byte HEAD_OPERATION_CMD= 0x21; 
	public byte HEAD_REFERECE_CMD= 0x3F;
	public byte HEAD_RESP= 0x40;
	public byte HEAD_ACK= 0x06;
	
	public short UNIT_DLA_RS46 = (short) 0x8901;
	
	//End
	public byte TRAIL = 0x0A;
	
	//commands
	public short CMD_CONNECTION_CHECK = 0x0000;
	
	public short CMD_POWER = 0x5057;
	public byte ARG_POWER_OFF = 0x30;
	public byte ARG_POWER_ON = 0x31;
	public byte ARG_POWER_COOL_DOWN = 0x32;
	public byte ARG_POWER_ERROR = 0x34;
	
	public short CMD_INPUT = 0x4950;
	public byte ARG_INPUT_COMP = 0x32;
	public byte ARG_INPUT_PC = 0x33;
	public byte ARG_INPUT_HDMI1 = 0x36;
	public byte ARG_INPUT_HDMI2 = 0x37;
	
	public short CMD_REMOTE = 0x5243;
	
	public int BTN_STAND_BY = 0x37333036 ;
	public int BTN_ON = 0x37333035;
	public int BTN_HDMI1 = 0x37333730;
	public int BTN_HDMI2 = 0x37333731 ;
	public int BTN_COMP = 0x37333444 ;
	public int BTN_PC = 0x37333436 ;
	public int BTN_3D_FORMAT = 0x37334436 ;
	public int BTN_3D_SETTING = 0x37334435;
	public int BTN_LENS_CONTROL = 0x37333330;
	public int BTN_LENS_MEMORY = 0x37334434;
	public int BTN_LENS_AP = 0x37333230;
	public int BTN_ANAMO = 0x37334335;
	public int BTN_HIDE = 0x37333144;
	public int BTN_UP = 0x37333031;
	public int BTN_DOWN = 0x37333032 ;
	public int BTN_RIGHT = 0x37333334;
	public int BTN_LEFT = 0x37333336;
	public int BTN_OK = 0x37333246;
	public int BTN_MENU = 0x37333245;
	public int BTN_BACK = 0x37333033 ;
	public int BTN_FILM = 0x37333639 ;
	public int BTN_CINEMA = 0x37333638 ;
	public int BTN_ANIME = 0x37333636 ;
	public int BTN_NATURAL = 0x37333641 ;
	public int BTN_STAGE = 0x37333637 ;
	public int BTN_3D = 0x37333837 ;
	public int BTN_THX = 0x37333646 ;
	public int BTN_USER = 0x37334437 ;
	public int BTN_CMD = 0x37333841 ;
	public int BTN_MPC = 0x37334630 ;
	public int BTN_INFO = 0x37333736 ;
	public int BTN_GAMMA = 0x37333735;
	public int BTN_COLOR_TEMP = 0x37333736;
	public int BTN_COLOR_PROF = 0x37333838;
	public int BTN_COLOR_SPACE = 0x37334344;
	public int BTN_PIC_ADJ = 0x37333732;
}
