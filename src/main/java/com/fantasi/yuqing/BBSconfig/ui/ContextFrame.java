package com.fantasi.yuqing.BBSconfig.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.fantasi.yuqing.BBSconfig.assembly.BBSfactory;
import com.fantasi.yuqing.BBSconfig.assembly.Factory;
import com.fantasi.yuqing.BBSconfig.assembly.Sitefactory;
import com.fantasi.yuqing.BBSconfig.assembly.Tianyafactory;
import com.fantasi.yuqing.BBSconfig.assembly.Tiebafactory;
import com.fantasi.yuqing.BBSconfig.assembly.Xicifactory;
import com.fantasi.yuqing.BBSconfig.bean.BroadConfig;
import com.fantasi.yuqing.BBSconfig.bean.Broads;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ContextFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//模块类型
	private String type;
	private List<BroadConfig> broadConfigs;
	private Broads broads;
	private JPanel commitButton;
	private JTextField cityName;
	private JTextField level2Name;
	private JTextField defaultGenus;
	private JTextField hostName;
	private JTextField interval;
	private JTextField modelName;
	private JTextField modelUrl;
	private JTextField genus;
	//button_1为提交按钮
	private JButton addButton,showButton,commitBBB;				

	/**
	 * Create the frame.
	 */
	public ContextFrame(String type) {
		this.type = type;
		broadConfigs = new ArrayList<BroadConfig>();
		broads = new Broads();
		setTitle("配置");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 649, 617);
		setVisible(true);
		commitButton = new JPanel();
		commitButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(commitButton);
		commitButton.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 618, 59);
		commitButton.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("市名：");
		lblNewLabel.setBounds(10, 8, 50, 15);
		lblNewLabel.setToolTipText("");
		panel.add(lblNewLabel);
		
		cityName = new JTextField();
		cityName.setBounds(70, 5, 66, 21);
		panel.add(cityName);
		cityName.setColumns(10);
		
		JLabel label = new JLabel("县（市）名：");
		label.setBounds(146, 8, 83, 15);
		panel.add(label);
		
		level2Name = new JTextField();
		level2Name.setBounds(239, 5, 66, 21);
		panel.add(level2Name);
		level2Name.setColumns(10);
		
		JLabel lblgenus = new JLabel("默认genus:");
		lblgenus.setBounds(315, 8, 77, 15);
		panel.add(lblgenus);
		
		defaultGenus = new JTextField();
		defaultGenus.setBounds(402, 5, 66, 21);
		panel.add(defaultGenus);
		defaultGenus.setColumns(10);
		
		JLabel label_1 = new JLabel("网站名：");
		label_1.setBounds(10, 34, 57, 15);
		panel.add(label_1);
		
		hostName = new JTextField();
		hostName.setBounds(70, 31, 66, 21);
		panel.add(hostName);
		hostName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("采集频率：");
		lblNewLabel_3.setBounds(146, 34, 83, 15);
		panel.add(lblNewLabel_3);
		
		interval = new JTextField();
		interval.setBounds(239, 31, 66, 21);
		panel.add(interval);
		interval.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 74, 618, 83);
		commitButton.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("默认genus为市区号，去掉首位数字0后的加c。");
		lblNewLabel_1.setBounds(10, 5, 344, 15);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("比如无锡的区号为0510，那么genus就为c510,下属县的genus请联系研发索要");
		lblNewLabel_2.setBounds(10, 30, 455, 15);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("采集频率根据，论坛发帖回帖速度，决定，一般最久不多于30分钟（填写30）最快不快于5（5），建议20或30之间");
		lblNewLabel_4.setBounds(10, 55, 608, 15);
		panel_1.add(lblNewLabel_4);
		
		JPanel commit = new JPanel();
		commit.setBounds(5, 167, 618, 255);
		commitButton.add(commit);
		commit.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("版块名：");
		lblNewLabel_5.setBounds(10, 28, 54, 15);
		commit.add(lblNewLabel_5);
		
		modelName = new JTextField();
		modelName.setBounds(74, 25, 534, 32);
		commit.add(modelName);
		modelName.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("版块URL：");
		lblNewLabel_6.setBounds(10, 85, 54, 15);
		commit.add(lblNewLabel_6);
		
		modelUrl = new JTextField();
		modelUrl.setBounds(74, 82, 534, 32);
		commit.add(modelUrl);
		modelUrl.setColumns(10);
		
		JLabel lblGenus = new JLabel("genus(为市级论坛时，可不填)");
		lblGenus.setBounds(10, 145, 202, 15);
		commit.add(lblGenus);
		
		genus = new JTextField();
		genus.setBounds(190, 142, 118, 32);
		commit.add(genus);
		genus.setColumns(10);
		
		addButton = new JButton("添加");
		addButton.setBounds(222, 208, 93, 23);
		commit.add(addButton);
		addButton.addActionListener(this);
		
		showButton = new JButton("查看已添加版块");
		showButton.setBounds(335, 208, 131, 23);
		commit.add(showButton);
		
		commitBBB = new JButton("提交");
		commitBBB.setBounds(500, 208, 93, 23);
		commit.add(commitBBB);
		commitBBB.addActionListener(this);
		showButton.addActionListener(this);
		
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addButton){
			if(!(modelName.getText().equals(null))&&(!modelUrl.getText().equals(null))){
				BroadConfig broadConfig = new BroadConfig();
				broadConfig.setName(modelName.getText());
				broadConfig.setUrl(modelUrl.getText().replaceAll("&", "&amp;"));
				if(genus.getText().equals("")||(genus.getText().equals(null))){
					broadConfig.setGenus(defaultGenus.getText());
				}else{
					broadConfig.setGenus(genus.getText());
				}
				broadConfigs.add(broadConfig);
				modelName.setText(null);
				modelUrl.setText(null);
				//genus.setText(null);
			}
		}else if(e.getSource()==showButton){
			//时间来的急再做处理
			//System.out.println("不要这么莫名其妙好吧");
		}else if(e.getSource()==commitBBB){
			//System.out.print("证明这个按钮是有用的");
			broads.setBroadConfigs(broadConfigs);
			broads.setCityName(cityName.getText());
			broads.setCityLevel_2Name(level2Name.getText());
			broads.setInteraval((interval.getText()));
			broads.setDefaultGenus(defaultGenus.getText());
			broads.setH1("nameTieba".equals(type));
			broads.setPeople("people".equals(type));
			broads.setSiteName(hostName.getText());
			
			Factory factory = null;
			if("bbs".equals(type)){
				factory = new BBSfactory(broads);
			}else if("site".equals(type)||"people".equals(type)){
				factory = new Sitefactory(broads);
			}else if("nameTieba".equals(type)||"otherTieba".equals(type)){
				factory = new Tiebafactory(broads);
			}else if("xici".equals(type)){
				factory = new Xicifactory(broads);
			}else if("tianya".equals(type)){
				factory = new Tianyafactory(broads);
			}
			factory.assemblyLine();
			this.dispose();
		}
	}
}
