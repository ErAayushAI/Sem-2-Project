package DataStructure;

import Model.Feedback;

//Custom Linked List For Feedback.
public class FeedbackLinkedList {
    private FeedbackNode head;

    // Add feedback to the end of the list
    public void addFeedback(Feedback feedback) {
        FeedbackNode newNode = new FeedbackNode(feedback);
        if (head == null) {
            head = newNode;
        } else {
            FeedbackNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Display all feedbacks
    public void displayFeedbacks() {
        FeedbackNode current = head;
        while (current != null) {
            Feedback fb = current.data;
            System.out.println("ID: " + fb.getId() +
                    ", User: " + fb.getUserId() +
                    ", Place: " + fb.getPlaceId() +
                    ", Rating: " + fb.getStarRating() +
                    ", Comments: " + fb.getComments());
            current = current.next;
        }
    }

    // Search feedback by user ID
    public void searchByUserId(int userId) {
        FeedbackNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.data.getUserId() == userId) {
                Feedback fb = current.data;
                System.out.println("Found: " + fb.getComments() + " (" + fb.getStarRating() + ")");
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No feedback found for User ID: " + userId);
        }
    }
}

//To create New Node of Feedback in Linked List.
class FeedbackNode {
    Feedback data;
    FeedbackNode next;

    public FeedbackNode(Feedback data) {
        this.data = data;
        this.next = null;
    }
}