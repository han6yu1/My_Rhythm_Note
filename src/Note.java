import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.ImageIcon;
import javax.swing.RepaintManager;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

//노트가 내려오는 클래스
public class Note extends Thread{
	String imagePath = System.getProperty("user.dir")+"/src/images/";  //이미지 상대 경로
	Image noteImage = new ImageIcon(imagePath+"Note.png").getImage();  //노트 이미지
	int x, y = 100;  //노트의 x, y 위치
	String noteType;  //노트의 타입
	int startTime;  //노트가 나오는 시간
	
	private static final int BPM = 150; // test Super Shy BPM 값 설정
	private static final int PIXELS_PER_BEAT = (int)(900/(60.0 / BPM));  // 한 비트당 움직여야 할 거리 계산
	
	public Note(NoteList notelist) {
		this.noteType = notelist.getNoteType();
		this.startTime = notelist.getStartTime();
		 
		//노트의 타입으로 x좌표의 위치를 정함
		if(noteType.equals("S")) {
			this.x = 80; 
		}
		if(noteType.equals("D")) {
			this.x = 290; 
		}
		if(noteType.equals("F")) {
			this.x = 500; 
		}
		if(noteType.equals("J")) {
			this.x = 710; 
		}
		if(noteType.equals("K")) {
			this.x = 920; 
		}
		if(noteType.equals("L")) {
			this.x = 1130; 
		}
		
	}
	
	//getter
	public int getY() {
		return y;
	}
	public String getNoteType() {
		return noteType;
	}

	//노트를 그래픽에 그림
	public void drawNote(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  //안티 앨리어싱 설정(화질 좋아지게)
		g.setClip(0, 200, 1400, 800);  //노트가 나오는 구역 설정
		g.drawImage(noteImage, this.x, this.y,200,130,null);  //그래픽에 노트 그려줌
	}
	
	//노트의 y좌표를 증가시켜줌
	@Override
	public void run() {
		try {
			Thread.sleep(startTime);  //노트가 startTime이 지나고 등장함
			while(true) {
				this.y += 10;  //y좌표를 증가
				Thread.sleep(10); 
			}//while
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//test
	}
}
