@notifications
Feature: Notifications funksionallığının yoxlanılması


  @markAllAsRead @smoke @regression
  Scenario: İstifadəçi bütün notification-ları oxunmuş edir

    Given İstifadəçi Notifications səhifəsindədir
    When İstifadəçi Mark all as read düyməsinin vəziyyətini yoxlayır
    Then Bütün notification-lar oxunmuş olmalıdır


  @deleteSingleNotification @regression
  Scenario: İstifadəçi bir notification-u silir

    Given İstifadəçi Notifications səhifəsindədir
    And Notification listində ən azı bir notification mövcuddur
    When İstifadəçi bir notification-un Delete düyməsinə klik edir
    Then Notification sayı bir ədəd azalmalıdır


  @deleteAllNotifications @regression
  Scenario: İstifadəçi bütün notification-ları silir

    Given İstifadəçi Notifications səhifəsindədir
    And Notification listində ən azı bir notification mövcuddur
    When İstifadəçi Delete all düyməsinə klik edir
    Then Delete all confirmation pəncərəsi açılmalıdır
    When İstifadəçi confirmation pəncərəsində Delete all düyməsinə klik edir
    Then Bütün notification-lar silinməlidir