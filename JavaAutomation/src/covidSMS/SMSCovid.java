package covidSMS;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.mail.*;
import javax.mail.internet.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.activation.*;


public class SMSCovid {


	public static void main(String[] args) {
		String to = "phonenumberhere@phoneprovideraddress";
		String from = "email-address-here";
		String host = "smtp.gmail.com";
		String cases = "";
		String deaths = "";
		String total = "";
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
	    properties.put("mail.smtp.port", "465");
	    properties.put("mail.smtp.ssl.enable", "true");
	    properties.put("mail.smtp.auth", "true");
	    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "gmail-app-password-here");

            }

        });
	    session.setDebug(true);
	    WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://usafacts.org/visualizations/coronavirus-covid-19-spread-map/state/illinois");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement getCases = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[2]/div/div[1]/div/div[1]/p[3]"));
            WebElement getDeaths = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[2]/div/div[1]/div/div[2]/p[3]"));
            WebElement getTotalCases = driver.findElement(By.xpath("/html/body/div/div/div[4]/div[2]/div/div[1]/div/div[1]/p[2]"));
            total += getTotalCases.getText();
            cases += getCases.getText().replaceAll("[^0-9,]","");
            deaths += getDeaths.getText().replaceAll("[^0-9,]","");
        } finally {
            driver.quit();
        }
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setText("Cases:" + cases + "\nDeaths: " + deaths);
			
			Transport.send(message);
			System.out.println("Message Sent!");
			
			
		}catch(MessagingException E) {
			E.printStackTrace();
		}


	}

}
