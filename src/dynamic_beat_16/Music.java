package dynamic_beat_16;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player;
	private boolean isLoop;		// ���� ���� ���ѹݺ����� �ѹ� ����ϰ� ��������
	private File file;			
	private FileInputStream fis;		//���Ϸκ��� ����Ʈ�� �Է¹޾�, ����Ʈ ������ ���
	private BufferedInputStream bis;	//FilterStream�� ����Ͽ� ���� ���� ����� �����ϴ� Ŭ����
	
	public Music(String name, boolean isLoop) {
		try {									//����ó�� ��뱸��
			this.isLoop = isLoop;				//isLoop ���� �ʱ�ȭ
			file = new File(Main.class.getResource("../music/" + name).toURI());	//�ش��̸��� ���� ����
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {		// � ��ġ�� ����Ǵ��� �˷���
		if (player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() {		//���� ����
		isLoop = false;
		player.close();
		this.interrupt();		//�ش� �����带 �������·� ����
	}
	
	@Override
	public void run() {			//�����带 ��ӹ����� ������ ����ؾߵǴ� �Լ�
		try {
			do {
				player.play();	//�뷡 ����
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);	//isLoop�� true���� ������ �� ���� �ݺ�
		} catch (Exception e) {
			System.out.println(e.getMessage());		// ������ �߻��� ��� �ش� �����޼��� �߼�
		}
	}

}
