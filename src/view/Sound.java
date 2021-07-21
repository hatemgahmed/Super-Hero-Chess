package view;

import java.applet.*;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	private static File wavFile = new File("back-music.wav");
	private static AudioClip sound;
	private static File wavFileBatman = new File("im-batman.wav");
	private static AudioClip batmanSound;
	private static File wavFileVenom = new File("We are Venom.wav");
	private static AudioClip venomSound;
	private static File wavFileSalah = new File("salah2.wav");
	private static AudioClip salahSound;
	private static File wavFileGroot = new File("I am Groot5.wav");
	private static AudioClip grootSound;
	private static File wavFileDarthVader = new File("darkside2.wav");
	private static AudioClip DarthVaderSound;
	private static File wavFileGuko = new File("Goku.wav");
	private static AudioClip GukoSound;
	private static File wavFileThanos = new File("Thanos.wav");
	private static AudioClip ThanosSound;
	private static File wavFileCap = new File("Captain America.wav");
	private static AudioClip CapSound;
	private static File wavFileJoker = new File("Joker.wav");
	private static AudioClip JokerSound;
	private static File wavFileStorm = new File("Stormtrooper.wav");
	private static AudioClip StormSound;
	private static File wavFileBB8 = new File("BB8.wav");
	private static AudioClip BB8Sound;
	private static File wavFileVoldemort = new File("Voldemort.wav");
	private static AudioClip VoldemortSound;
	private static File wavFileArrow = new File("Green Arrow.wav");
	private static AudioClip ArrowSound;
	private static File wavFileDeadshot = new File("Deadshot.wav");
	private static AudioClip DeadshotSound;
	static Clip clip = null;
	
	public static AudioClip getGrootSound() {
		try {
			grootSound = Applet.newAudioClip(wavFileGroot.toURL());
			AudioInputStream a=null;
			try {
				 a = AudioSystem.getAudioInputStream(new File("I am Groot5.wav"));
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(a);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FloatControl g = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			g.setValue(6.0206f);
			clip.start();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//grootSound.play();
		return grootSound;
	}
	
	public static AudioClip getSalahSound() {
		try {
			salahSound = Applet.newAudioClip(wavFileSalah.toURL());
			AudioInputStream a=null;
			try {
				 a = AudioSystem.getAudioInputStream(new File("salah2.wav"));
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(a);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FloatControl g = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			g.setValue(-10.0f);
			clip.start();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//salahSound.play();
		return salahSound;
	}
	public static AudioClip getGukoSound() {
		try {
			GukoSound = Applet.newAudioClip(wavFileGuko.toURL());
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		GukoSound.play();
		return GukoSound;
	}
	public static AudioClip getArrowSound() {
		try {
			ArrowSound = Applet.newAudioClip(wavFileArrow.toURL());
			AudioInputStream a=null;
			try {
				 a = AudioSystem.getAudioInputStream(new File("Green Arrow.wav"));
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(a);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FloatControl g = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			g.setValue(6.0206f);
			clip.start();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//ArrowSound.play();
		return ArrowSound;
	}
	public static AudioClip getDeadshotSound() {
		try {
			DeadshotSound = Applet.newAudioClip(wavFileDeadshot.toURL());
			AudioInputStream a=null;
			try {
				 a = AudioSystem.getAudioInputStream(new File("Deadshot.wav"));
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(a);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FloatControl g = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			g.setValue(6.0206f);
			clip.start();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//DeadshotSound.play();
		return DeadshotSound;
	}
	public static AudioClip getBB8Sound() {
		try {
			BB8Sound = Applet.newAudioClip(wavFileBB8.toURL());
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		BB8Sound.play();
		return BB8Sound;
	}
	public static AudioClip getVoldemortSound() {
		try {
			VoldemortSound = Applet.newAudioClip(wavFileVoldemort.toURL());
			AudioInputStream a=null;
			try {
				 a = AudioSystem.getAudioInputStream(new File("Voldemort.wav"));
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(a);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FloatControl g = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			g.setValue(6.0206f);
			clip.start();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//VoldemortSound.play();
		return VoldemortSound;
	}
	public static AudioClip getCapSound() {
		try {
			CapSound = Applet.newAudioClip(wavFileCap.toURL());
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		CapSound.play();
		return CapSound;
	}
	public static AudioClip getJokerSound() {
		try {
			JokerSound = Applet.newAudioClip(wavFileJoker.toURL());
			AudioInputStream a=null;
			try {
				 a = AudioSystem.getAudioInputStream(new File("Joker.wav"));
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(a);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FloatControl g = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			g.setValue(6.0206f);
			clip.start();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//JokerSound.play();
		return JokerSound;
	}
	public static AudioClip getStormSound() {
		try {
			StormSound = Applet.newAudioClip(wavFileStorm.toURL());
			AudioInputStream a=null;
			try {
				 a = AudioSystem.getAudioInputStream(new File("Stormtrooper.wav"));
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(a);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FloatControl g = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			g.setValue(6.0206f);
			clip.start();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//StormSound.play();
		return StormSound;
	}
	public static AudioClip getThanosSound() {
		try {
			ThanosSound = Applet.newAudioClip(wavFileThanos.toURL());
			AudioInputStream a=null;
			try {
				 a = AudioSystem.getAudioInputStream(new File("Thanos.wav"));
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(a);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FloatControl g = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			g.setValue(6.0206f);
			clip.start();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//ThanosSound.play();
		return ThanosSound;
	}
	public static AudioClip getDarthVaderSound() {
		try {
			DarthVaderSound = Applet.newAudioClip(wavFileDarthVader.toURL());
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		DarthVaderSound.play();
		return DarthVaderSound;
	}
	public static AudioClip getVenomSound() {
		try {
			venomSound = Applet.newAudioClip(wavFileVenom.toURL());
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		venomSound.play();
		return venomSound;
	}
	public static AudioClip getBatmanSound() {
		try {
			batmanSound = Applet.newAudioClip(wavFileBatman.toURL());
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		batmanSound.play();
		return batmanSound;
	}
	public static AudioClip getSound() {
		
		return sound;
		
	}
	@SuppressWarnings("deprecation")
	public static void play(){
    	try {
    		AudioInputStream a=null;
			try {
				 a = AudioSystem.getAudioInputStream(new File("back-music.wav"));
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				clip.open(a);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FloatControl g = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			g.setValue(-8.0f);
			clip.start();
			clip.loop(100);
			sound = Applet.newAudioClip(wavFile.toURL());
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	//sound.loop();
    	//sound.play();
	}
	public static void stop() {
		clip.stop();
	}
	
}
