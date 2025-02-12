import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public static void main(String[] args) {
    SwingUtilities.invokeLater(ContactManagementApp::new);
}

public ContactManagementApp() {
    contacts = new ArrayList<>();
    listModel = new DefaultListModel<>();
    contactList = new JList<>(listModel);


    frame = new JFrame("Contact Management System");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 400);
    frame.setLayout(new BorderLayout());



    cardLayout = new CardLayout();
    cardPanel = new JPanel(cardLayout);


    cardPanel.add(createContactListView(), "Contact List");
    cardPanel.add(createContactDetailsView(), "Contact Details");
    cardPanel.add(createContactCreationForm(), "Contact Creation");

    frame.add(cardPanel, BorderLayout.CENTER);

    frame.setVisible(true);
}


private JPanel createContactListView() {
    JPanel panel = new JPanel(new BorderLayout());


    contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollPane = new JScrollPane(contactList);
    panel.add(scrollPane, BorderLayout.CENTER);


    JPanel buttonPanel = new JPanel();
    JButton addContactButton = new JButton("Add New Contact");
    JButton viewDetailsButton = new JButton("View Details");

    addContactButton.addActionListener(e -> cardLayout.show(cardPanel, "Contact Creation"));

    viewDetailsButton.addActionListener(e -> {
        Contact selectedContact = contactList.getSelectedValue();
        if (selectedContact != null) {
            cardLayout.show(cardPanel, "Contact Details");
            updateContactDetailsView(selectedContact);
        }
    });

    buttonPanel.add(addContactButton);
    buttonPanel.add(viewDetailsButton);
    panel.add(buttonPanel, BorderLayout.SOUTH);



    return panel;
}


private JPanel createContactDetailsView() {
    JPanel panel = new JPanel(new BorderLayout());

    JLabel detailsLabel = new JLabel("Contact Details", JLabel.CENTER);
    panel.add(detailsLabel, BorderLayout.NORTH);

    JTextArea detailsArea = new JTextArea(5, 20);
    detailsArea.setEditable(false);
    panel.add(new JScrollPane(detailsArea), BorderLayout.CENTER);



    JButton backToListButton = new JButton("Back to List");
    backToListButton.addActionListener(e -> cardLayout.show(cardPanel, "Contact List"));

    panel.add(backToListButton, BorderLayout.SOUTH);

    return panel;
}


private JPanel createContactCreationForm() {
    JPanel panel = new JPanel(new GridLayout(4, 2));


    JTextField nameField = new JTextField();
    JTextField phoneField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField background = new JTextField();

    panel.add(new JLabel("Name:"));
    panel.add(nameField);
    panel.add(new JLabel("Phone:"));
    panel.add(phoneField);
    panel.add(new JLabel("Email:"));
    panel.add(emailField);
    panel.setbackground(Color.BLUE);



    JButton saveButton = new JButton("Save Contact");
    JButton cancelButton = new JButton("Cancel");

    saveButton.addActionListener(e -> {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            Contact newContact = new Contact(name, phone, email);
            contacts.add(newContact);
            listModel.addElement(newContact);
            cardLayout.show(cardPanel, "Contact List");
        } else {
            JOptionPane.showMessageDialog(frame, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    cancelButton.addActionListener(e -> cardLayout.show(cardPanel, "Contact List"));

    panel.add(saveButton);
    panel.add(cancelButton);

    return panel;
}


private void updateContactDetailsView(Contact contact) {
    JPanel detailsPanel = (JPanel) cardPanel.getComponent(1);  // Contact Details Panel
    JTextArea detailsArea = (JTextArea) ((JScrollPane) detailsPanel.getComponent(1)).getViewport().getView();
    detailsArea.setText("Name: " + contact.getName() + "\nPhone: " + contact.getPhone() + "\nEmail: " + contact.getEmail());
}


