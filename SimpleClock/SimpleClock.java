package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ItemEvent;
import java.util.TimeZone;


public class SimpleClock extends JFrame implements ItemListener {
    
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        JToggleButton timeZoneToggle;
        JToggleButton militaryToggle;
        String time;
        String day;
        String date;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(450, 220);
            this.setResizable(false);

            calendar.getTimeZone();
    
            timeFormat = new SimpleDateFormat(" hh:mm:ss a ");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            timeZoneToggle = new JToggleButton("GMT");
            timeZoneToggle.addItemListener(this);

            militaryToggle = new JToggleButton("24hr");
            militaryToggle.addItemListener(this);

            this.add(timeLabel);
            this.add(timeZoneToggle);
            this.add(dayLabel);
            this.add(militaryToggle);
            this.add(dateLabel);
            this.setVisible(true);

            run();
//            setTimer();
        }

//    public void setTimer() {
//            while (true) {
//                time = timeFormat.format(Calendar.getInstance().getTime());
//                timeLabel.setText(time);
//
//                day = dayFormat.format(Calendar.getInstance().getTime());
//                dayLabel.setText(day);
//
//                date = dateFormat.format(Calendar.getInstance().getTime());
//                dateLabel.setText(date);
//
//                try {
//                    Thread.sleep(100);
//                } catch (Exception e) {
//                    e.getStackTrace();
//                }
//            }
//        }
        public static void main(String[] args) {
            new SimpleClock();
        }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (militaryToggle.isSelected()) {
            militaryToggle.setText("12hr");
            timeFormat = new SimpleDateFormat("   kk:mm:ss   ");
            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        }
        if (!militaryToggle.isSelected()) {
            militaryToggle.setText("24hr");
            timeFormat = new SimpleDateFormat(" hh:mm:ss a ");
        }
        if (timeZoneToggle.isSelected()) {
            timeZoneToggle.setText("EST");
            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
            dayFormat = new SimpleDateFormat("EEEE");
            dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
            if (militaryToggle.isSelected()) timeFormat = new SimpleDateFormat("   kk:mm:ss   ");
            if (!militaryToggle.isSelected()) timeFormat = new SimpleDateFormat(" hh:mm:ss a ");
        }
        if (!timeZoneToggle.isSelected()) {
            timeZoneToggle.setText("GMT");
            TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
            dayFormat = new SimpleDateFormat("EEEE");
            dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
            if (militaryToggle.isSelected()) timeFormat = new SimpleDateFormat("   kk:mm:ss   ");
            if (!militaryToggle.isSelected()) timeFormat = new SimpleDateFormat(" hh:mm:ss a ");
        }
    }


    public void run() {
        time = timeFormat.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);

        day = dayFormat.format(Calendar.getInstance().getTime());
        dayLabel.setText(day);

        date = dateFormat.format(Calendar.getInstance().getTime());
        dateLabel.setText(date);

        Thread thread = new Thread(this::run);
        thread.start();

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.getStackTrace();
        }
        super.repaint();
    }
}