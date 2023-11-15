package org.acme.demo.notifications;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationSenderTest {
    @Mock
    private Connection connMock;

    @Mock
    private PreparedStatement preparedStatementMock;


    @Test
    void whenNotAlreadySent_thenNotificationIsSent() throws SQLException {
        when(connMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        NotificationSender sender = new NotificationSender();
        sender = spy(sender);

        boolean status = sender.sendDailyNotification(connMock);
        assertTrue(status);

        verify(sender, times(1)).processNotificationsAndSendEmails();
    }

    @Test
    void whenAlreadySent_onlyOneNotificationIsSent() throws SQLException {
        when(connMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate())
                .thenReturn(1)
                .thenThrow(new SQLIntegrityConstraintViolationException());

        NotificationSender sender = new NotificationSender();
        sender = spy(sender);

        boolean status = sender.sendDailyNotification(connMock);
        assertTrue(status);
        status = sender.sendDailyNotification(connMock);

        verify(sender, times(1)).processNotificationsAndSendEmails();
        assertFalse(status);
    }
}