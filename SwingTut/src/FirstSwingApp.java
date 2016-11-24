import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FirstSwingApp extends JFrame implements ActionListener{

	JButton jbtOK;
	JLabel lbl1;
	JTextField jText;
	
	public FirstSwingApp() {
		super();
		// TODO Auto-generated constructor stub
		jbtOK = new JButton("OK");
		jbtOK.addActionListener(this);
		lbl1 = new JLabel("Enter your name");
		jText = new JTextField(100);
		Font font = new Font("Serif", Font.ITALIC, 24);
		lbl1.setFont(font);
		this.add(jbtOK);
		this.add(lbl1);
		this.add(jText);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 3));
		this.add(panel);
		panel.setSize(300, 300);
		JLabel label2 = new JLabel("In the panel");
		panel.add(label2);
		JLabel label3 = new JLabel("Next panel");
		panel.add(label3);
		panel.setLayout(new FlowLayout());
		this.add(panel);
		this.setBounds(100, 100, 400, 250);
		this.setLayout(new FlowLayout());
		this.setTitle("It doesnt mean a thing");
		this.setVisible(true);
		this.setSize(800, 800);
	}
	
	public static void main(String[] args) {
		FirstSwingApp app = new FirstSwingApp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
					JButton src = (JButton) e.getSource();
					src.setText("Pressed");
	}

}
