package healthApp.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import healthApp.data.showDates;
import healthApp.model.Reminder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;

public class ReminderController implements Initializable{

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private GridPane menuButton;
	@FXML
	private GridPane calendar;
	@FXML
	private Pane detailsPane;
	@FXML
	private Button nextMonthButton;
	@FXML
	private Button prevMonthButton;
	@FXML
	private Label calendarTitle;

	
	int monthCount;

	
	@FXML
	public void goToEditReminder(ActionEvent event) {
		// TODO Autogenerated
		try {
			Parent newRoot = FXMLLoader.load(getClass().getResource("/healthApp/view/EditReminder.fxml"));
			anchorPane.getScene().setRoot(newRoot);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void prevMonth(ActionEvent event) {
		showDates.prevMonth(calendar);
		
		changeMonthHelper();
	}
	
	@FXML
	public void nextMonth(ActionEvent event) {
		showDates.nextMonth(calendar);
		
		changeMonthHelper();
	}
	
	public void changeMonthHelper() {
		//shares this function between the nextmonth and prevmonth functions.
		int year =showDates.calYear(); //current year + offset years (month/12 rounded up)
		
		monthCount = showDates.monthCount-1; //gets the month of the previous month button, to avoid indexOutOfBounds with -1.
		if(monthCount <0) monthCount = (monthCount % 12) + 12; //if month is negative, change it to the proper positive value (-1 = 11, because before January is December)
		
		changeCalendarLabel(showDates.MONTHS[monthCount % 12], showDates.MONTHS[(monthCount+1)%12]  + " " + year, showDates.MONTHS[(monthCount+2) % showDates.MONTHS.length]);
		
		
	}

	public void changeCalendarLabel(String prevMonth, String month, String nextMonth) {
		calendarTitle.setText(month);
		prevMonthButton.setText(prevMonth);
		nextMonthButton.setText(nextMonth);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int month = showDates.monthStringToInt(showDates.today[1]);
		int year = Integer.parseInt(showDates.today[2]);
		showDates.showMonth(month,year,calendar);
		
		showDates.monthCount = showDates.monthStringToInt(showDates.today[1]);
		
		int monthCount = showDates.monthCount-1;
		if(monthCount < 0) {monthCount = monthCount% 12 + 12;}
		changeCalendarLabel(showDates.MONTHS[monthCount], showDates.MONTHS[month] + " " + showDates.today[2], showDates.MONTHS[(monthCount+2) % 12]);
		
		
		//THIS CODE SHOULD BE ON THE HOMEPAGE (WHEREVER THE USER ACCESSES FIRST)
		startReminderCheck(0, 0); //run once when app opens
		startReminderCheck((60- LocalTime.now().getSecond()) * 1000, 60000); //run again on next minute, then repeat every second.
	}
	
	
	
	public void startReminderCheck(int delay, int repetition) {
		try {
		for(Reminder r : showDates.get2DaysReminders()) { //searches through the reminders for today, repeating every minute
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							LocalTime rTime = LocalTime.parse(r.getTime());
							LocalTime now = LocalTime.now();
							LocalDate rDate = LocalDate.parse(r.getDate());
							
							if(rDate.getDayOfWeek()!= LocalDate.now().getDayOfWeek()) //if the reminder is for tmr, skip
								return;
							else if(rTime.getHour() == now.getHour() && rTime.getMinute() == now.getMinute()) {
								//only compare minute and hour (no need seconds)
								Alert alert = new Alert(AlertType.INFORMATION, r.getName(), ButtonType.OK);
								alert.showAndWait();
							}
						}
						
					});
				}
			},delay, repetition);
		} 
		}catch(IllegalArgumentException e) {
			//if the repetition <= 0, that means that there is no need to repeat. just delay.
			for(Reminder r : showDates.get2DaysReminders()) { //searches through the reminders for today, repeating every minute
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								LocalTime rTime = LocalTime.parse(r.getTime());
								LocalTime now = LocalTime.now();
								LocalDate rDate = LocalDate.parse(r.getDate());
								
								if(rDate.getDayOfWeek()!= LocalDate.now().getDayOfWeek()) //if the reminder is for tmr, skip
									return;
								else if(rTime.getHour() == now.getHour() && rTime.getMinute() == now.getMinute()) {
									//only compare minute and hour (no need seconds)
									Alert alert = new Alert(AlertType.INFORMATION, r.getName(), ButtonType.OK);
									alert.showAndWait();
								}
							}
							
						});
					}
				},delay);
			}
		}
	}
}
