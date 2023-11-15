package org.acme.demo.notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotificationSender {

    /**
     * Returns true if notification process completes successfully, false otherwise
     * @param connection
     * @return true|false
     */
    public boolean sendDailyNotification(Connection connection) {
        System.out.println("Starting notification process...");

        if (notificationProcessAlreadyStarted(connection)) {
            System.out.println("Daily notification process already started, skipping");
             return false;
        }

        processNotificationsAndSendEmails();

        return true;
    }

    protected void processNotificationsAndSendEmails() {
        System.out.println("Notification process started...");
        System.out.println("Sending notifications email...");
        System.out.println("All notification emails were sent");
        System.out.println("Notification process ended");
    }

    private boolean notificationProcessAlreadyStarted(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into mytable (col1, col2, col3) values (?, ?, ?)");

            preparedStatement.setString(1, "14-11-2024");
            preparedStatement.setString(2, "Daily email");
            preparedStatement.setString(3, "Whatever you want");

            // Execute the SQL statement
            preparedStatement.executeUpdate();

            return false;
        } catch (SQLException e) {
            // row not inserted
            System.out.println("Daily notification process already started, skipping");
            return true;
        }
    }
}
