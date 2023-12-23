package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class CommentTest {

    @Test
    public void testGetCurrentDate() {
        Comment comment = new Comment();
        String expectedDateFormat = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        String currentDate = comment.getCurrentDate();
        assertTrue(currentDate.matches(expectedDateFormat));
    }

    @Test
    public void testAddUserVote() {
        Comment comment = new Comment();
        comment.addUserVote("user1", "like");
        comment.addUserVote("user2", "dislike");
        comment.addUserVote("user3", "like");

        Map<String, String> expectedUserVotes = new HashMap<>();
        expectedUserVotes.put("user1", "like");
        expectedUserVotes.put("user2", "dislike");
        expectedUserVotes.put("user3", "like");

        assertEquals(expectedUserVotes, comment.getUserVote());
        assertEquals(2, comment.getLike());
        assertEquals(1, comment.getDislike());
    }
}