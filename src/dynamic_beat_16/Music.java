package dynamic_beat_16;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player;
	private boolean isLoop;		// 현재 곡이 무한반복인지 한번 재생하고 꺼지는지
	private File file;			
	private FileInputStream fis;		//파일로부터 바이트로 입력받아, 바이트 단위로 출력
	private BufferedInputStream bis;	//FilterStream을 상속하여 실제 필터 기능을 제공하는 클래스
	
	public Music(String name, boolean isLoop) {
		try {									//예외처리 사용구문
			this.isLoop = isLoop;				//isLoop 변수 초기화
			file = new File(Main.class.getResource("../music/" + name).toURI());	//해당이름의 파일 실행
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {		// 어떤 위치에 실행되는지 알려줌
		if (player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() {		//음악 종료
		isLoop = false;
		player.close();
		this.interrupt();		//해당 쓰레드를 중지상태로 만듦
	}
	
	@Override
	public void run() {			//쓰레드를 상속받으면 무조건 사용해야되는 함수
		try {
			do {
				player.play();	//노래 실행
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);	//isLoop가 true값을 가지면 곡 무한 반복
		} catch (Exception e) {
			System.out.println(e.getMessage());		// 오류가 발생한 경우 해당 오류메세지 발송
		}
	}

}
