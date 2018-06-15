package com.fantasi.yuqing.BBSconfig.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fantasi.yuqing.BBSconfig.util.AppContext;

public class Mform  implements ActionListener{
	static final int WIDTH  = 618;
	static final int HEIGHT = 382;
	JButton bbs = new JButton("论坛");
	JButton site = new JButton("新闻或站点");
	JButton nameTieba = new JButton("地名贴吧");
	JButton otherTieba = new JButton("其他贴吧");
	JButton xici = new JButton("西祠");
	JButton people = new JButton("人民网留言板");
	JButton tianya = new JButton("天涯");
	public void init(){
		JFrame frame = new JFrame("论坛配置器v1.0");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		panel.add(bbs);
		panel.add(site);
		panel.add(nameTieba);
		panel.add(otherTieba);
		panel.add(xici);
		panel.add(people);
		panel.add(tianya);
		frame.setVisible(true);
		frame.setResizable(false);
		bbs.addActionListener(this);
		site.addActionListener(this);
		nameTieba.addActionListener(this);
		otherTieba.addActionListener(this);
		xici.addActionListener(this);
		people.addActionListener(this);
		tianya.addActionListener(this);
	}
	
	public static void main(String[] args) {
		AppContext.init();
		Mform mfrom = new Mform();
		mfrom.init();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bbs){
			@SuppressWarnings("unused")
			ContextFrame bbsFrame = new ContextFrame("bbs");
		}else if(e.getSource()==site){
			@SuppressWarnings("unused")
			ContextFrame bbsFrame = new ContextFrame("site");
		}else if(e.getSource()==nameTieba){
			@SuppressWarnings("unused")
			ContextFrame bbsFrame = new ContextFrame("nameTieba");
		}else if(e.getSource()==otherTieba){
			@SuppressWarnings("unused")
			ContextFrame bbsFrame = new ContextFrame("otherTieba");
		}else if(e.getSource()==xici){
			@SuppressWarnings("unused")
			ContextFrame bbsFrame = new ContextFrame("xici");
		}else if(e.getSource()==people){
			@SuppressWarnings("unused")
			ContextFrame bbsFrame = new ContextFrame("people");
		}else if(e.getSource()==tianya){
			@SuppressWarnings("unused")
			ContextFrame bbsFrame = new ContextFrame("tianya");
		}	
	}
}
