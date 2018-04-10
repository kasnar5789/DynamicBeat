package dynamic_beat_16;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 3;		//노트의 스피드
	public static final int SLEEP_TIME = 10;	//노트 떨어지는 주기
	public static final int REACH_TIME = 2;		// 도달 시간
	
	public static void main(String[] args) {
		
		new DynamicBeat();

	}

}
