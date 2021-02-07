package healthApp.model;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.imageio.ImageIO;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String userName;
	private String email;
	private String password;
	private String health;
	private String location;
	private byte[] image;
	private String imageType;
	private BufferedImage bImage;
	private String adminName = "adminOnly";
	private String adminPassword = "justAdminsToUse";
	private String adminEmail = "adminOnly@gmail.com";
	
	public Users(String name, String userName, String mailInput, String toggleOn, String health, String location, String bufferedImage)  {
		this.name = name;
		this.userName = userName;
		this.email = mailInput;
		this.password = toggleOn;
		this.health = health;
		this.location = location;
		this.bImage = bImage;
	}
	
	public String getAdminName() {
		return adminName = "adminOnly";
	}

	public String getAdminPassword() {
		return adminPassword = "justAdminsToUse";
	}

	public String getAdminEmail() {
		return adminEmail = "adminOnly@gmail.com";
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public BufferedImage getImage() {
		InputStream in = new ByteArrayInputStream(this.image);
		BufferedImage bImageFromConvert=null;
		try {
			bImageFromConvert = ImageIO.read(in);
		} catch (IOException e) {
			System.out.println("Failed to get image!!\n"+e.getMessage());
		}
		return bImageFromConvert;
	}
	
	public void setImage(BufferedImage image, String type) {
		 try (ByteArrayOutputStream out=new ByteArrayOutputStream()){
			 ImageIO.write(image, type, out);
			 this.image=out.toByteArray();
			 this.setImageType(type);
		 } catch (IOException e) {
			 System.out.println("Failed to set image!!\n"+e.getMessage());
		 }
	}
	
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
}
