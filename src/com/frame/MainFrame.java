package com.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;

import com.freemarker.result.format.HtmlFreemarker;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.helper.EnvHelper;
import com.helper.JarHelper;
import com.helper.ServiceHelper;
import com.helper.Statistics;
import com.junit.JunitReader;
import com.junit.JunitWriter;
import com.junit.Testsuite;
import com.listener.NumberJTextField;
import com.log.Log;
import com.model.ArgBean;
import com.model.ArgModel;
import com.model.MobileModel;
import com.model.RowBean;
import com.model.scriptTableModel;
import com.model.tableModel;
import com.panel.SettingDb;
import com.panel.scriptPanel;
import com.report.Report;
import com.server.ServerSocket;
import com.thread.UIAutomatorThread;
import com.ui.Constant;
import com.ui.TextFile;
import com.xml.LogBean;
import com.xml.LogXmlReader;
import com.xml.LogXmlWriterl;
import com.xml.UiBean;
import com.thread.Lock;
public class MainFrame implements ActionListener {
	public static ArrayList<String> devname;
	public static List<String> devErrorList;
	public static boolean deviceIsOk;
	// 设置窗口宽度
	public static int w = 1200;
	// 设置高度
	public static int h = 600;

	public static boolean stop = false;
	public static boolean termiante = false;
	public static JDialog progressDialog;
	public static TableModelListener tableModelListener;
	public static TableModelListener scripttableModelListener;
	private JMenuBar menuBar;
	private JPanel toolBarPanel;
	private JPanel toolkpiPanel;
	public static JTextField starTextField;
	public static JTextField endTextField;
	public static JRadioButton selectAll;
	public static JRadioButton selectAllSP;
	public static JCheckBox logCheckBox;
	public static  JRadioButton editSelect;
	public static  JRadioButton editSummary;
	public static  JRadioButton editInteration;
	public static  JRadioButton editAdjust;
	public static  JRadioButton editKpi;
	public static  JRadioButton editStandard;
	public static TableRowSorter<TableModel> sorter;
	public static JButton scrpitsAdd;
	public static JButton scrpitsImport;
	public static JButton scrpitsDel,script_device;
	public static JButton scrpitsExport;
	public static JButton bt_down,bt_up,bt_random,bt_clear;
	public static JButton upButton;
	public static JButton downButton;
	public static JButton spUpButton;
	public static JButton spDownButton;
	public static JButton dataDel;
	public static JButton report;
	public static JButton reportMobile;
	public static JButton btnexport;
	public static JButton AntJunit;
	public static JButton btnstop;
	public static JButton btnexportLog;
	//public static JButton replay_select;
	public static JButton recoder;
	public static JButton replay;
	public static JButton replayAll;
	public static JButton constantscript;
	public static JButton parseAndAnalyze;
	public static JButton videoParsetoImage;
	public static JButton AnalyzeImage;
	public static JButton AnalyzeData;
	public static JButton DeleteAnalyzeData;
	public static JComboBox<String> devjcombox ;
	public static ArrayList<String> devicesList =new ArrayList<String>();
	public static JComboBox<String> adddevjcombox;
	public static JComboBox<String> addsetcombox;
	private static JTabbedPane mainjTabbedPane;
	public static JButton TelephonyBtn;
	private static JDialog spDialog;
	public static JTable spTable;
	public static JTable scriptTable;
	public static JTable argTable;
	public static JTable mbLogTable;
	public static ArgModel argModel;
	public static MobileModel mobileModel;
	public static scriptTableModel scriptTableModel;
	//sp table
	//video
	public static DefaultListModel<String> videosListModel;
	public static JList<String> list;
	public static JTextField totalfld,passfld,failfld,notrunfld,tf_filter_path;
	public static JTextField stotalfld,spassfld,sfailfld,snotrunfld;
	static Dimension dimensionstatics =new Dimension(40, 20);
	static Dimension dimension =new Dimension(150, 22);
	static Dimension dimension3 =new Dimension(100, 22);
	static Dimension filterdimension =new Dimension(400, 22);
	Dimension dimensionhash = new Dimension(200, 22);
	Dimension lbDimension = new Dimension(110, 22);

	Dimension lbDimension2 =new Dimension(60, 22);
	//Dimension editdmension =new Dimension(75, 20);

	private scriptPanel spUiPanel;
	public static JProgressBar bar;
	public static JLabel runLabel;
	public static tableModel tbpModel;

	//log jdialog
	private static JDialog configDialog ;
	private static JDialog mobileLogDialog ;
	private static JDialog configArgDialog ;
	private JDialog ResultDialog;
	public static JFrame frame;
	public static JTextPane textPane;

	private static Thread runthread;

	public MainFrame() {
		//super();
		frame = new JFrame();
		frame.setTitle("UIAutomator1.0 CTool For V3");
		// 激活窗口事件
		//enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		w=(int)toolkit.getScreenSize().getWidth()-100;
		h=(int)toolkit.getScreenSize().getHeight()-100;
		frame.setBounds(100, 100, w, h);
		int x = (int) (toolkit.getScreenSize().getWidth() - w) / 2;
		int y = (int) (toolkit.getScreenSize().getHeight() - h) / 2;
		frame.setLocation(x, y);// 设置窗口居中
		frame.setJMenuBar(getJMenuBar());// 设置菜单项
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);
		//frame.getContentPane().add(getLogPanel(),BorderLayout.CENTER);
		frame.getContentPane().add(geTabbedPane(),BorderLayout.PAGE_START);

		frame.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		/*try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());
			SubstanceLookAndFeel.setSkin(new EmeraldDuskSkin());
			SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
			SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
			SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
			SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
		} catch (Exception e) {
			System.err.println("Something went wrong!");
		}*/
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				processWindowEvent(e);
			}
		});

	}
	public static void init_Antbuild_xml(){
		String antfile = JarHelper.getProjectPath()+"build.xml";
		if (!new File(antfile).exists()) {
			StringBuffer contentBuffer = new StringBuffer();
			contentBuffer.append("<project name=\"TestNG_WORKSPACE\" default=\"junit-report\" basedir=\".\">\n");
			contentBuffer.append("<!-- Sets the property variables to point to respective directories -->\n");
			contentBuffer.append("<property name=\"junit-xml-dir\" value=\"${basedir}/test-output/junitreports\"/>\n");
			contentBuffer.append("<property name=\"report-dir\" value=\"${basedir}/html-report\" />\n");
			contentBuffer.append("<!-- Ant target to generate html report -->\n");
			contentBuffer.append("<target name=\"junit-report\">\n");
			contentBuffer.append("<!-- Delete and recreate the html report directories -->\n");
			contentBuffer.append("<delete dir=\"${report-dir}\" failonerror=\"false\"/>\n");
			contentBuffer.append("<mkdir dir=\"${report-dir}\" />\n");
			contentBuffer.append("<mkdir dir=\"${report-dir}/Junit\" />\n");
			contentBuffer.append("<!-- Ant task to generate the html report.\n");
			contentBuffer.append("todir - Directory to generate the output reports\n");
			contentBuffer.append("fileset - Directory to look for the junit xml reports.\n");
			contentBuffer.append("report - defines the type of format to be generated.\n");
			contentBuffer.append("Here we are using \"noframes\" which generates a single html report.\n");
			contentBuffer.append("-->\n");
			contentBuffer.append("<junitreport todir=\"${report-dir}/Junit\">\n");
			contentBuffer.append("<fileset dir=\"${basedir}/junit\">\n");
			contentBuffer.append("<include name=\"*.xml\" />\n");
			contentBuffer.append("</fileset>\n");
			contentBuffer.append("<report format=\"noframes\" todir=\"${report-dir}/Junit\" />\n");
			contentBuffer.append("</junitreport>\n");
			contentBuffer.append("</target>\n");
			contentBuffer.append("</project>\n");
			ServiceHelper.writeToFile2(antfile, contentBuffer.toString(), false);
		}
	}
	public static void init_ATT_Enviroment(){
		//String logXml = JarHelper.getProjectPath()+"log\\log.xml";
		//String logXmlDir = JarHelper.getProjectPath()+"log";
		//String configXmlDir = JarHelper.getProjectPath()+"config";
		//String configXml = JarHelper.getProjectPath()+"config\\config.xml";
		String data = JarHelper.getProjectPath()+"data";
		String jar = JarHelper.getProjectPath()+"jar";
		String scripts = JarHelper.getProjectPath()+"scripts";
		String report = JarHelper.getProjectPath()+"report";
		String mobilelog = JarHelper.getProjectPath()+"mobile_log";
		String sourceDir = JarHelper.getProjectPath()+"source";
		String logcatDir = JarHelper.getProjectPath()+"logcat";

		new File(JarHelper.getProjectPath()).mkdirs();

		File logcatDir_f = new File(logcatDir);
		if (!logcatDir_f.exists()) {
			logcatDir_f.mkdirs();
		}

		File sourceDir_f = new File(sourceDir);
		if (!sourceDir_f.exists()) {
			sourceDir_f.mkdirs();
		}

		File data_f = new File(data);
		if (!data_f.exists()) {
			data_f.mkdirs();
		}
		File jar_f = new File(jar);
		if (!jar_f.exists()) {
			jar_f.mkdirs();
		}
		File scripts_f = new File(scripts);
		if (!scripts_f.exists()) {
			scripts_f.mkdirs();
		}

		File report_f = new File(report);
		if (!report_f.exists()) {
			report_f.mkdirs();
		}
		File mobile_log_f = new File(mobilelog);
		if (!mobile_log_f.exists()) {
			mobile_log_f.mkdirs();
		}
		/*String antLoc = EnvHelper.getEnv("ANT_HOME");
		if ("".equals(antLoc)) {
			JOptionPane.showMessageDialog(new JFrame(), "请配置ANT_HOME环境变量!","提示", JOptionPane.INFORMATION_MESSAGE);
			SettingDialog(new JFrame());
		}*/
	}
	public JTable getscriptTable(){
		scriptTableModel = new scriptTableModel(20);
		scriptTable = new JTable(scriptTableModel){
			private static final long serialVersionUID = 1L;
		};

		scriptTableModel.construct();

		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scriptTable.getTableHeader().setReorderingAllowed(false);
		scriptTable.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (!e.isTemporary()) {
					//scriptTable.clearSelection();
				}
			}
		});

		int selected = scriptTable.getSelectedRow();
		if (selected>=0) {
			String cmdstr = scriptTable.getValueAt(selected, 5).toString();
			scriptTable.setToolTipText("<HTML><BODY bgcolor=\"#00FFCC\">" +
					"<P>双击Jtable查看日志文件" +"<BR/>" +cmdstr+"<BR/>"+"</P>" +
					"</BODY></HTML>");

		}else {
			scriptTable.setToolTipText("<HTML><BODY bgcolor=\"#00FFCC\">" +
					"<P>双击Jtable查看日志文件" +"<BR/>" +"</P>" +
					"</BODY></HTML>");

		}
		scripttableModelListener = new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				final int col = e.getColumn();                   
				final int row = e.getFirstRow();    
				if (col==1) {
					Thread thread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							String isDisplay = scriptTable.getValueAt(row, col).toString();
							String testcase_id = scriptTable.getValueAt(row, 7).toString();
							try {
								Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
								Table table = qdb.getTable("log");
								for (Row lrow : table) {
									if (testcase_id.equals(lrow.get("id_testcase"))) {
										lrow.put("isDisplay", isDisplay);
										table.updateRow(lrow);
									}
								}
								qdb.close();
							} catch (Exception e2) {
								// TODO: handle exception
							}
							write_select_script_count();
						}
					});
					thread.start();
				}
			}
		};
		scriptTableModel.addTableModelListener(scripttableModelListener);

		/*sorter = new TableRowSorter<TableModel>(scriptTableModel);
		scriptTable.setRowSorter(sorter);*/

		scriptTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

				int row = scriptTable.getSelectedRow();
				if (row!=-1) {
					String cmdstr = scriptTable.getValueAt(row, 5).toString();
					scriptTable.setToolTipText("<HTML><BODY bgcolor=\"#00FFCC\">" +
							cmdstr+"<BR/>"+"</P>" +
							"</BODY></HTML>");

				}else {
					scriptTable.setToolTipText("<HTML><BODY bgcolor=\"#00FFCC\">" +
							"<P>双击Jtable查看日志文件" +"<BR/>" +"</P>" +
							"</BODY></HTML>");

				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					int row = scriptTable.getSelectedRow();
					if (row!=-1) {
						String random = scriptTable.getValueAt(row, 7).toString();
						LogBean logBean = new LogBean();
						logBean.setRandomid(random);
						try {
							Database db = new DatabaseBuilder(new File("Test.mdb")).open();
							Table logtable = db.getTable("log");
							for (Row row2 : logtable) {
								if (random.equals(row2.get("id_testcase").toString())){
									String LogArgument = row2.get("LogArgument").toString();
									logBean.setLogAugument(LogArgument);
								}
							}
						} catch (Exception e2) {
							// TODO: handle exception
						}
						try {
							UIResultDialog(frame, logBean);
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		scriptTable.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode()==KeyEvent.VK_L&&e.isControlDown()) {
					int row= scriptTable.getSelectedRow();
					if (row!=-1) {
						String randomtxt= scriptTable.getValueAt(row, 7).toString();
						String logcatFile = JarHelper.getProjectPath()+"\\logcat"+randomtxt+".txt";
						Log.info("Logcat日志文件:"+logcatFile);
					}
				}

				if (e.getKeyCode()==KeyEvent.VK_P&&e.isControlDown()) {
					ArrayList<Integer> rows= scriptTableModel.getSelectRows();
					if (rows.size()>=1) {
						if(JOptionPane.showConfirmDialog(new JFrame(),"确定重新设置脚本的结果?", 
								"Warning",JOptionPane.YES_NO_OPTION) == 0){
							modifyDialog();
						}
					}else {
						JOptionPane.showMessageDialog(new JFrame(), "没有选择脚本!","提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		TableColumnModel tcm = scriptTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(20);
		tcm.getColumn(1).setPreferredWidth(40);
		tcm.getColumn(2).setPreferredWidth(280);
		tcm.getColumn(3).setPreferredWidth(150);
		tcm.getColumn(4).setPreferredWidth(80);
		tcm.getColumn(5).setPreferredWidth(130);
		tcm.getColumn(6).setPreferredWidth(130);
		tcm.getColumn(7).setPreferredWidth(130);
		DefaultTableCellRenderer tcr6 = new DefaultTableCellRenderer(){
			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				if (String.valueOf(value).contains("FAIL")){
					setForeground(Color.red);						
				}else if (String.valueOf(value).contains("TERMINATE")){
					setForeground(Color.red);	
				}else if (String.valueOf(value).contains("PASS")){
					setForeground(Color.BLUE);
				}else{
					setForeground(Color.BLACK);
				}
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};
		tcr6.setHorizontalAlignment(SwingConstants.CENTER);

		scriptTable.getColumnModel().getColumn(6).setCellRenderer(tcr6); 
		scriptTable.setRowHeight(20);  	

		JComboBox<String> devjbox = new JComboBox<String>();
		int rowcount = scriptTable.getRowCount();
		Set<String> devset=new HashSet<String>();
		for (int t = 0; t < rowcount; t++) {
			String dev = scriptTable.getValueAt(t, 4).toString();
			devset.add(dev);
		}
		devset.addAll(devicesList);
		for (String dev : devset) {
			devjbox.addItem(dev);
		}
		//scriptTable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(devjbox));

		scriptTable.repaint();

		JTableHeader tableHeader = scriptTable.getTableHeader();
		tableHeader.setReorderingAllowed(false);   //设置表格列不可重排
		DefaultTableCellRenderer hr =(DefaultTableCellRenderer)tableHeader.getDefaultRenderer();  //获得表格头的单元格对象
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);  //列名居中

		scriptTable.getTableHeader().setResizingAllowed(true);
		scriptTable.setRowSelectionAllowed(true);
		scriptTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		return scriptTable;
	}
	public JScrollPane getRunTablePanel(){
		JScrollPane sptableScrollPane = new JScrollPane(getscriptTable());
		int w = 2*(MainFrame.w-10)/5;
		int h = 2*(MainFrame.h-10)/5;
		sptableScrollPane.setPreferredSize(new Dimension(w,h));
		return sptableScrollPane;
	}
	/*public static void constructTable(tableModel tbpModel ){
		//tbpModel.content.removeAllElements();
		//spTable.updateUI();
		tbpModel.construct();
		try {
			Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
			Table table = qdb.getTable("config");

			for(Row row : table) {  
				tbpModel.addRow(
						Boolean.parseBoolean(row.get("isSelect").toString()),
						row.get("script").toString(), 
						row.get("summary").toString(), 
						row.get("es").toString(), 
						row.get("randomid").toString(), 
						row.get("device").toString(), 
						row.get("suitName").toString());
			} 
			qdb.close();
			spTable.updateUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public static void constructArgumentTable(int row){
		String eString = tbpModel.getValueAt(row, 4).toString();
		if (eString.equals("NA")) {

		}else {
			String[] es = eString.split("\\|");
			for (String estr : es) {
				System.out.println(estr);
				if (estr.startsWith("-E:")) {
					String arg= estr.replace("-E:", "");
					if (arg.length()>=1) {
						argModel.addRow(arg, "NA", "NA");
					}
				}else {
					String[] arg2 = estr.split("_");
					if (arg2.length==3) {
						String argument = arg2[0];
						String type     = arg2[1];
						String value    = arg2[2];
						argModel.addRow(argument, type, value);
					}
				}
			}
		}
	}
	public static void construct_log_xml(ArrayList<String> randArrayList,String randomid){
		String logxml = JarHelper.getProjectPath()+"log\\log.xml";
		LogXmlReader logmy = new LogXmlReader();
		Vector<LogBean> LogBeans = logmy.toRead(logxml);

		Vector<LogBean> LogBeansNew = new Vector<LogBean>();

		for (String random : randArrayList) {
			LogBean bean = new LogBean();
			bean.setRandomid(random);
			for (LogBean lbean : LogBeans) {
				if (lbean.getRandomid().equals(random)) {
					if (random.startsWith(randomid)) {
						//do nothing 
					}else {
						bean.setLogAugument(lbean.getLogAugument());
						bean.setSuitName(lbean.getSuitName());
						bean.setCaseName(lbean.getCaseName());
						bean.setSummary(lbean.getSummary());
						break;
					}
				}
			}
			LogBeansNew.add(bean);
		}

		try{
			LogXmlWriterl myxml=new LogXmlWriterl(logxml);
			System.out.println("start swrite xml file:");
			myxml.toWrite(LogBeansNew);
			myxml.toSave();
			System.out.print("Your writing is successful.");
		}
		catch(ParserConfigurationException exp){
			exp.printStackTrace();
			System.out.print("Your writing is failed.");
		}
	}
	public static void construct_log_xml(ArrayList<String> randArrayList){
		String logxml = JarHelper.getProjectPath()+"log\\log.xml";
		LogXmlReader logmy = new LogXmlReader();
		Vector<LogBean> LogBeans = logmy.toRead(logxml);

		Vector<LogBean> LogBeansNew = new Vector<LogBean>();

		for (String random : randArrayList) {
			LogBean bean = new LogBean();
			bean.setRandomid(random);
			for (LogBean lbean : LogBeans) {
				if (lbean.getRandomid().equals(random)) {
					bean.setLogAugument(lbean.getLogAugument());
					bean.setSuitName(lbean.getSuitName());
					bean.setCaseName(lbean.getCaseName());
					bean.setSummary(lbean.getSummary());
					break;
				}
			}
			LogBeansNew.add(bean);
		}

		try{
			LogXmlWriterl myxml=new LogXmlWriterl(logxml);
			System.out.println("start swrite xml file:");
			myxml.toWrite(LogBeansNew);
			myxml.toSave();
			System.out.print("Your writing is successful.");
		}
		catch(ParserConfigurationException exp){
			exp.printStackTrace();
			System.out.print("Your writing is failed.");
		}
	}
	public static  void initValue(ArgBean argBean){
		if (argBean.getArgType().equals("FILE")) {
			String filename = argBean.getArgValue();
			List<String> reads = TextFile.read(filename);
			argBean.setValue(reads);
		}else if (argBean.getArgType().equals("RANGE")) {
			System.out.println(argBean.getArgType());
			System.out.println("["+argBean.getArgValue()+"]");
			long start = Long.parseLong(argBean.getArgValue().split("-")[0].trim());
			long end =  Long.parseLong( argBean.getArgValue().split("-")[1].trim());
			List<String> reads = new ArrayList<String>();
			for (long i = start; i <=end; i++) {
				reads.add(i+"");
			}
			argBean.setValue(reads);
		}else if (argBean.getArgType().equals("VALUE")) {
			List<String> reads = new ArrayList<String>();
			reads.add(argBean.getArgValue());
			argBean.setValue(reads);
		}else {
			List<String> reads = new ArrayList<String>();
			reads.add(argBean.getArgValue());
			argBean.setValue(reads);
		}

	}
	public static void write_select_script_count(){
		Statistics stcs = new Statistics();
		int tests=0; 
		int pass=0;
		int fail=0;
		int notrun=0;
		int terminate=0;
		try {
			Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
			Table logtable = qdb.getTable("log");
			for (Row row : logtable) {
				String result = row.get("result").toString();
				String isDisplay = row.get("isDisplay").toString();
				String isSelect = row.get("isSelect").toString();
				if ("true".equals(isDisplay)) {
					if ("true".equals(isSelect)) {
						tests = tests+1;
						if ("PASS".equals(result.toUpperCase())) {
							pass = pass+1;
						}else if ("FAIL".equals(result.toUpperCase())) {
							fail = fail+1;
						}else if ("NOTRUN".equals(result.toUpperCase())) {
							notrun = notrun+1;
						}else if ("terminate".equals(result.toLowerCase())) {
							terminate = terminate+1;
						}else {
						}
					}
				}
			}
			qdb.close();

			stcs.setTests(tests);
			stcs.setPass(pass);
			stcs.setFail(fail);
			stcs.setNotrun(notrun);
			stcs.setTerminate(terminate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		stotalfld.setText(stcs.getTests()+"");
		spassfld.setText(stcs.getPass()+"");
		sfailfld.setText(stcs.getFail()+"");
		snotrunfld.setText(stcs.getNotrun()+"");
		/*String c_tS = String.format("select tests %s,pass %s,fail %s,notrun %s,terminate %s",
				stcs.getTests(),
				stcs.getPass(),
				stcs.getFail(),
				stcs.getNotrun(),
				stcs.getTerminate());

		scriptPanel.tf_warn_suit.setText(c_tS);*/
	}
	public static void write_script_account(){
		Statistics stcs = new Statistics();
		int tests=0; 
		int pass=0;
		int fail=0;
		int notrun=0;
		int terminate=0;
		try {
			Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
			Table logtable = qdb.getTable("log");
			for (Row row : logtable) {
				String isSelect = row.get("isSelect").toString();
				if ("true".equals(isSelect)) {
					tests = tests+1;
					String result = row.get("result").toString();
					if ("PASS".equals(result.toUpperCase())) {
						pass = pass+1;
					}else if ("FAIL".equals(result.toUpperCase())) {
						fail = fail+1;
					}else if ("NOTRUN".equals(result.toUpperCase())) {
						notrun = notrun+1;
					}else if ("terminate".equals(result.toLowerCase())) {
						terminate = terminate+1;
					}else {
					}
				}
			}
			qdb.close();

			stcs.setTests(tests);
			stcs.setPass(pass);
			stcs.setFail(fail);
			stcs.setNotrun(notrun);
			stcs.setTerminate(terminate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalfld.setText(stcs.getTests()+"");
		passfld.setText(stcs.getPass()+"");
		failfld.setText(stcs.getFail()+"");
		notrunfld.setText(stcs.getNotrun()+"");

		/*String c_tS = String.format("total tests %s,pass %s,fail %s,notrun %s,terminate %s",
				stcs.getTests(),
				stcs.getPass(),
				stcs.getFail(),
				stcs.getNotrun(),
				stcs.getTerminate());*/

		//scriptPanel.tf_warn_path.setText(c_tS);
	}
	@SuppressWarnings("static-access")
	public static ArrayList<String> construct_script_table(){

		ArrayList<String> randomidArrayList = new ArrayList<>();
		System.out.println("start construct script table..........");

		scriptTableModel.content.removeAllElements();
		scriptTable.updateUI();
		scriptTableModel.construct();

		return randomidArrayList;
	}
	public static void delete_log_table(){
		try {
			Database db = new DatabaseBuilder(new File("Test.mdb")).open();
			Table logtable = db.getTable("log");
			for (Row row : logtable) {
				logtable.deleteRow(row);
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void construct_log_table(){

		Database db = null;
		Table table = null;
		Table logtable = null;
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");
			logtable = db.getTable("log");
		} catch (Exception e) {
			// TODO: handle exception
		}
		ArrayList<Object[]> objs = new ArrayList<>();
		for(Row row : table){
			System.out.println(row);

			String es = row.get("es").toString();
			String randomid_testsuit = row.get("randomid").toString();
			String isSelect = row.get("isSelect").toString();
			String suitName = row.get("suitName").toString();
			String summary = row.get("summary").toString();
			String device = row.get("device").toString();
			String script = row.get("script").toString();
			String LogArgument = "";
			String isDisplay = "false";
			String TimeStart = "";
			String TimeEnd ="";
			String result = "NOTRUN";

			ArrayList<ArgBean> argList = new ArrayList<ArgBean>();
			String[] cargs = es.split("\\|");
			for (String cs : cargs) {
				String[] csArg = cs.split("_");
				if (csArg.length==3) {
					ArgBean argBean = new ArgBean();
					argBean.setArgName(csArg[0]);
					argBean.setArgType(csArg[1]);
					argBean.setArgValue(csArg[2]);
					initValue(argBean);
					argList.add(argBean);
				}else {
					//if not exist: -e value
					ArgBean argBean = new ArgBean();
					argBean.setArgName(cs);
					argBean.setArgType("NA");
					argBean.setArgValue(cs);
					initValue(argBean);
					argList.add(argBean);
				}
			}
			int argCount = argList.size();
			int max= 0;
			for (int i = 0; i < argCount; i++) {
				ArgBean bean = argList.get(i);
				int count = bean.getValue().size();
				if (count>=max) {
					max=count;
				}
			}
			System.out.println("max data is : "+max);

			for (int i = 0; i < max; i++) {
				int indexNum = i+1;
				String id_testcase = randomid_testsuit+"_"+max+"_"+indexNum;
				String id_casename = summary+"_"+max+"_"+indexNum;

				//List<Object> rows = new ArrayList<Object>();

				try {
					for (Row queryrow : logtable) {
						if (id_testcase.equals(row.get("id_testcase"))) {
							LogArgument = queryrow.getString("queryrow").toString();
							isDisplay =  queryrow.getString("isDisplay").toString();
							TimeStart =  queryrow.getString("TimeStart").toString();
							TimeEnd =  queryrow.getString("TimeEnd").toString();
							result =  queryrow.getString("result").toString();
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				Object[] lrow = new Object[logtable.getColumnCount()];
				lrow[0]=randomid_testsuit;
				lrow[1]=isSelect;
				lrow[2]=id_testcase;
				lrow[3]=isDisplay;
				lrow[4]=suitName;
				lrow[5]=id_casename;
				lrow[6]=summary;
				lrow[7]=TimeStart;
				lrow[8]=TimeEnd;
				lrow[9]="calssname";
				lrow[10]=LogArgument;

				StringBuffer argBuffer = new StringBuffer();
				for (int j = 0; j < argCount; j++) {
					ArgBean bean = argList.get(j);
					String argname = bean.getArgName();
					String argvalue;
					List<String> reads = bean.getValue();
					if (indexNum<=reads.size()) {
						argvalue = reads.get(i);
					}else {
						argvalue = reads.get(0);
					}
					String eArg = String.format("-e %s %s ", argname,argvalue);
					argBuffer.append(eArg);
				}

				lrow[11]=argBuffer.toString();
				lrow[12]=device;
				lrow[13]=result;
				lrow[14]=script;
				//rows.add(lrow);
				for (int j = 0; j < lrow.length; j++) {
					System.out.println(i+":"+j+":"+lrow[j]);
				}
				/*try {
					logtable.addRow(lrow);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				objs.add(lrow);
				/*for(Row logrow : logtable){
					if (id_testcase.equals(
							logrow.get("id_testcase").toString())) {
						//rows.add(logrow);
						try {
							logtable.addRow((Object)logrow);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {

					}
				}*/

			}
		}
		try {
			db.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");
			logtable = db.getTable("log");

			for (Row drow : logtable) {
				logtable.deleteRow(drow);
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			table = db.getTable("config");
			logtable = db.getTable("log");
			for (Object[] object : objs) {
				logtable.addRow(object);
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void construct_log_table(Object[] row){

		Database db = null;
		Table logtable = null;
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");
		} catch (Exception e) {
			// TODO: handle exception
		}
		ArrayList<Object[]> objs = new ArrayList<>();
		String es = row[5].toString();
		String randomid_testsuit = row[4].toString();
		String isSelect = row[0].toString();
		String suitName = row[6].toString();
		String summary = row[2].toString();
		String device = row[3].toString();
		String script = row[1].toString();
		String LogArgument = "";
		String isDisplay = "false";
		String TimeStart = "";
		String TimeEnd ="";
		String result = "NOTRUN";

		ArrayList<ArgBean> argList = new ArrayList<ArgBean>();
		String[] cargs = es.split("\\|");
		for (String cs : cargs) {
			String[] csArg = cs.split("_");
			if (csArg.length==3) {
				ArgBean argBean = new ArgBean();
				argBean.setArgName(csArg[0]);
				argBean.setArgType(csArg[1]);
				argBean.setArgValue(csArg[2]);
				initValue(argBean);
				argList.add(argBean);
			}else {
				//if not exist: -e value
				ArgBean argBean = new ArgBean();
				argBean.setArgName(cs);
				argBean.setArgType("NA");
				argBean.setArgValue(cs);
				initValue(argBean);
				argList.add(argBean);
			}
		}
		int argCount = argList.size();
		int max= 0;
		for (int i = 0; i < argCount; i++) {
			ArgBean bean = argList.get(i);
			int count = bean.getValue().size();
			if (count>=max) {
				max=count;
			}
		}
		System.out.println("max data is : "+max);

		for (int i = 0; i < max; i++) {
			int indexNum = i+1;
			String id_testcase = randomid_testsuit+"_"+max+"_"+indexNum;
			String id_casename = summary+"_"+max+"_"+indexNum;
			Object[] lrow = new Object[logtable.getColumnCount()];
			lrow[0]=randomid_testsuit;
			lrow[1]=isSelect;
			lrow[2]=id_testcase;
			lrow[3]=isDisplay;
			lrow[4]=suitName;
			lrow[5]=id_casename;
			lrow[6]=summary;
			lrow[7]=TimeStart;
			lrow[8]=TimeEnd;
			lrow[9]="calssname";
			lrow[10]=LogArgument;

			StringBuffer argBuffer = new StringBuffer();
			for (int j = 0; j < argCount; j++) {
				ArgBean bean = argList.get(j);
				String argname = bean.getArgName();
				String argvalue;
				List<String> reads = bean.getValue();
				if (indexNum<=reads.size()) {
					argvalue = reads.get(i);
				}else {
					argvalue = reads.get(0);
				}
				String eArg = String.format("-e %s %s ", argname,argvalue);
				argBuffer.append(eArg);
			}

			lrow[11]=argBuffer.toString();
			lrow[12]=device;
			lrow[13]=result;
			lrow[14]=script;
			objs.add(lrow);
		}
		try {
			db.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			db = new DatabaseBuilder(new File("Test.mdb")).open();
			logtable = db.getTable("log");
			for (Object[] object : objs) {
				logtable.addRow(object);
			}
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static JTable getTable(){
		tbpModel = new tableModel(20);
		spTable = new JTable(tbpModel){
			private static final long serialVersionUID = 1L;
		};
		spTable.getTableHeader().setReorderingAllowed(false);
		TableColumnModel tcm = spTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(10);
		tcm.getColumn(1).setPreferredWidth(30);
		tcm.getColumn(2).setPreferredWidth(300);
		tcm.getColumn(3).setPreferredWidth(150);
		tcm.getColumn(4).setPreferredWidth(150);
		tcm.getColumn(5).setPreferredWidth(150);
		//tcm.getColumn(6).setPreferredWidth(150);
		//tcm.getColumn(7).setPreferredWidth(150);
		spTable.setRowHeight(20);  	
		//spTable.getTableHeader().setReorderingAllowed(false);

		//spTable.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());

		tbpModel.construct();

		tableModelListener = new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				final int col = e.getColumn();                   
				final int row = e.getFirstRow();  
				if (col==3) {
					// TODO Auto-generated method stub
					final String randomid = spTable.getValueAt(row, 5).toString();
					final String summary =spTable.getValueAt(row, col).toString();
					start_doing();
					Thread thread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Database db;
							Table table;
							Table logTable;
							try {
								db = new DatabaseBuilder(new File("Test.mdb")).open();
								table = db.getTable("config");
								for (Row row : table) {
									String id_testsuit = row.getString("randomid").toString();
									if (randomid.equals(id_testsuit)) {
										row.put("summary", summary);
										table.updateRow(row);
									}	
								}
								db.close();

								db = new DatabaseBuilder(new File("Test.mdb")).open();
								logTable = db.getTable("log");
								for (Row row : logTable) {
									String id_testsuit = row.getString("id_testsuit").toString();
									if (randomid.equals(id_testsuit)) {
										row.put("summary", summary);
										logTable.updateRow(row);
									}	
								}
								db.close();

								scriptTableModel.construct();
								scriptTable.updateUI();
								//constructTable(tbpModel);

								write_script_account();
								write_select_script_count();

							} catch (Exception e2) {
								// TODO: handle exception
							}
							stop_doing();
						}
					});
					thread.start();
				}
				if (col==1) {
					start_doing();
					Thread thread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							String randomid = spTable.getValueAt(row, 5).toString();
							String isselect =spTable.getValueAt(row, col).toString();
							Database db;
							Table table;
							Table logTable;
							try {
								db = new DatabaseBuilder(new File("Test.mdb")).open();
								table = db.getTable("config");
								for (Row row : table) {
									String status = row.getString("randomid").toString();
									if (randomid.equals(status)) {
										row.put("isSelect", isselect);
										table.updateRow(row);
										System.out.println("update config..............");
									}
								}
								db.close();

								db = new DatabaseBuilder(new File("Test.mdb")).open();
								logTable = db.getTable("log");
								for (Row row : logTable) {
									String id_testsuit = row.getString("id_testsuit").toString();
									if (randomid.equals(id_testsuit)) {
										row.put("isSelect", isselect);
										logTable.updateRow(row);
										System.out.println("update log..............");
									}
								}
								db.close();

								scriptTableModel.construct();
								scriptTable.updateUI();
								//constructTable(tbpModel);

								write_script_account();
								write_select_script_count();

							} catch (Exception e2) {
								// TODO: handle exception
							}
							stop_doing();
						}
					});
					thread.start();
				}
			}
		};
		spTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				/*int selected = spTable.getSelectedRow();
				if (selected>=0) {
					String cmdstr = spTable.getValueAt(selected, 4).toString();
					spTable.setToolTipText("<HTML><BODY bgcolor=\"#00FFCC\">" +
							cmdstr+"<BR/>"+"</P>" +
							"</BODY></HTML>");
				}*/
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		spTable.getModel().addTableModelListener(tableModelListener);

		JTableHeader tableHeader = spTable.getTableHeader();
		tableHeader.setReorderingAllowed(false);   //设置表格列不可重排
		DefaultTableCellRenderer hr =(DefaultTableCellRenderer)tableHeader.getDefaultRenderer();  //获得表格头的单元格对象
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);  //列名居中

		spTable.getTableHeader().setResizingAllowed(true);
		spTable.setRowSelectionAllowed(true);
		spTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );

		return spTable;
	}
	public JScrollPane getTableScrollPane(){
		JScrollPane tableJScrollPane = new JScrollPane(getTable());
		tableJScrollPane.setPreferredSize(new Dimension(w-10,h-100));
		return tableJScrollPane;
	}
	public JPanel getMainPanel(){
		JPanel mPanel = new JPanel();
		mPanel.setPreferredSize(new Dimension(w-10,h-100));
		mPanel.setLayout(new BorderLayout());
		mPanel.add(getkpiToolPanel(),BorderLayout.PAGE_START);
		mPanel.add(getTableScrollPane(),BorderLayout.CENTER);
		return mPanel;
	}
	public JPanel get3Panel(){
		JPanel mPanel = new JPanel();
		mPanel.setPreferredSize(new Dimension(w-10,h-100));
		mPanel.setLayout(new BorderLayout());
		mPanel.add(UiPanel(),BorderLayout.CENTER);
		//mPanel.add(getToolBar(),BorderLayout.NORTH);
		//mPanel.add(getRunTablePanel(),BorderLayout.SOUTH);
		return mPanel;
	}
	public JPanel get2Panel(){
		JPanel mPanel = new JPanel();
		mPanel.setPreferredSize(new Dimension(w-10,h-100));
		mPanel.setLayout(new BorderLayout());
		//mPanel.add(UiPanel(),BorderLayout.NORTH);
		mPanel.add(getToolBar(),BorderLayout.NORTH);
		mPanel.add(getRunTablePanel(),BorderLayout.CENTER);
		return mPanel;
	}
	public JTabbedPane geTabbedPane(){
		mainjTabbedPane=new JTabbedPane();
		mainjTabbedPane.add("脚本配置",getMainPanel());
		mainjTabbedPane.add("执行脚本",get2Panel());
		mainjTabbedPane.add("日志信息",get3Panel());

		return mainjTabbedPane;
	}
	public JPanel telephonyPanel(){
		JPanel  settingPanel = new JPanel();
		settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.PAGE_AXIS));

		settingPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel tJLabel = new JLabel("UIAutomator.jar");
		rowPanel.add(tJLabel);

		final JTextField jTextField = new JTextField();
		Dimension jTextFielddimension =new Dimension(500, 25);
		jTextField.setPreferredSize(jTextFielddimension);
		rowPanel.add(jTextField);

		JButton browserbtn = new JButton("选择");
		browserbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		JButton setbtn = new JButton("设置");

		rowPanel.add(browserbtn);
		rowPanel.add(setbtn);
		settingPanel.add(rowPanel);
		settingPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		Dimension dimension =new Dimension(150, 25);
		settingPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel instllLabel = new JLabel("安装测试数据:");
		JButton installBtn = new JButton("Install");
		installBtn.setPreferredSize(dimension);
		rowPanel.add(instllLabel);
		rowPanel.add(installBtn);
		settingPanel.add(rowPanel);

		return settingPanel;
	}
	private JPanel getToolBar() {
		if (toolBarPanel == null) {
			toolBarPanel = new JPanel();
			toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.PAGE_AXIS));
			toolBarPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

			JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			JLabel stotalbl = new JLabel("选择:");
			stotalbl.setFont(new Font("新宋体", Font.PLAIN, 12));
			stotalfld = new JTextField();
			stotalfld.setFont(new Font("新宋体", Font.PLAIN, 12));
			stotalfld.setEditable(false);
			stotalfld.setPreferredSize(dimensionstatics);

			JLabel spasslbl = new JLabel("pass:");
			spasslbl.setFont(new Font("新宋体", Font.PLAIN, 12));
			spassfld = new JTextField();
			spassfld.setEditable(false);
			spassfld.setPreferredSize(dimensionstatics);

			JLabel sfaillbl = new JLabel("fail:");
			sfaillbl.setFont(new Font("新宋体", Font.PLAIN, 12));
			sfailfld = new JTextField();
			sfailfld.setEditable(false);
			sfailfld.setPreferredSize(dimensionstatics);

			JLabel snotrunlbl = new JLabel("notrun:");
			snotrunlbl.setFont(new Font("新宋体", Font.PLAIN, 12));
			snotrunfld = new JTextField();
			snotrunfld.setEditable(false);
			snotrunfld.setPreferredSize(dimensionstatics);

			replay = new JButton("replay");
			replay.setPreferredSize(dimension);
			replay.setActionCommand("replay");
			replay.setFont(new Font("新宋体", Font.PLAIN, 12));
			replay.addActionListener(this);

			report = new JButton("生成测试报告");
			report.setPreferredSize(dimension);
			report.setActionCommand("report");
			report.setFont(new Font("新宋体", Font.PLAIN, 12));
			report.addActionListener(this);

			AntJunit = new JButton("report");
			AntJunit.setPreferredSize(dimension);
			AntJunit.setActionCommand("AntJunit");
			AntJunit.setFont(new Font("新宋体", Font.PLAIN, 12));
			AntJunit.addActionListener(this);


			rowPanel.add(stotalbl);
			rowPanel.add(stotalfld);
			rowPanel.add(spasslbl);
			rowPanel.add(spassfld);
			rowPanel.add(sfaillbl);
			rowPanel.add(sfailfld);
			rowPanel.add(snotrunlbl);
			rowPanel.add(snotrunfld);

			rowPanel.add(replay);
			rowPanel.add(AntJunit);

			toolBarPanel.add(rowPanel);

			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			JLabel totalbl = new JLabel("总共:");
			totalfld = new JTextField();
			totalfld.setEditable(false);
			totalfld.setPreferredSize(dimensionstatics);

			JLabel passlbl = new JLabel("pass:");
			passfld = new JTextField();
			passfld.setEditable(false);
			passfld.setPreferredSize(dimensionstatics);

			JLabel faillbl = new JLabel("fail:");
			failfld = new JTextField();
			failfld.setEditable(false);
			failfld.setPreferredSize(dimensionstatics);

			JLabel notrunlbl = new JLabel("notrun:");
			notrunfld = new JTextField();
			notrunfld.setEditable(false);
			notrunfld.setPreferredSize(dimensionstatics);

			bt_up = new JButton("move up");
			bt_up.setPreferredSize(dimension);
			bt_up.setActionCommand("bt_up");
			bt_up.setFont(new Font("新宋体", Font.PLAIN, 12));
			bt_up.addActionListener(this);

			bt_down = new JButton("move down");
			bt_down.setPreferredSize(dimension);
			bt_down.setActionCommand("bt_down");
			bt_down.setFont(new Font("新宋体", Font.PLAIN, 12));
			bt_down.addActionListener(this);

			bt_random = new JButton("random");
			bt_random.setPreferredSize(dimension);
			bt_random.setActionCommand("random");
			bt_random.setFont(new Font("新宋体", Font.PLAIN, 12));
			bt_random.addActionListener(this);


			bt_clear = new JButton("clear");
			bt_clear.setPreferredSize(dimension);
			bt_clear.setActionCommand("clear");
			bt_clear.setFont(new Font("新宋体", Font.PLAIN, 12));
			bt_clear.addActionListener(this);

			rowPanel.add(totalbl);
			rowPanel.add(totalfld);
			rowPanel.add(passlbl);
			rowPanel.add(passfld);
			rowPanel.add(faillbl);
			rowPanel.add(failfld);
			rowPanel.add(notrunlbl);
			rowPanel.add(notrunfld);
			rowPanel.add(bt_up);
			rowPanel.add(bt_down);
			rowPanel.add(bt_random);
			rowPanel.add(bt_clear);
			toolBarPanel.add(rowPanel);

			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			/*JLabel pcLabel = new JLabel("电脑端：");
			pcLabel.setPreferredSize(lbDimension2);*/

			btnstop = new JButton("停止执行脚本");
			btnstop.setPreferredSize(dimension);
			btnstop.setActionCommand("btnstop");
			btnstop.setFont(new Font("新宋体", Font.PLAIN, 12));
			btnstop.addActionListener(this);

			bar = new JProgressBar(0, 100);  
			bar.setPreferredSize(dimension);
			bar.setToolTipText("执行中.....");
			bar.setPreferredSize(new Dimension(400, 20));
			bar.setStringPainted(true); // 显示百分比字符
			bar.setIndeterminate(false); // 不确定的进度条

			runLabel = new JLabel("");

			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			/*JLabel mobLabel = new JLabel("手机端：");
			mobLabel.setPreferredSize(lbDimension2);*/

			btnexportLog = new JButton("export log");
			btnexportLog.setEnabled(false);
			btnexportLog.setPreferredSize(dimension);
			btnexportLog.setActionCommand("btnexportLog");
			btnexportLog.setFont(new Font("新宋体", Font.PLAIN, 12));
			btnexportLog.addActionListener(this);

			btnexport = new JButton("export script");
			btnexport.setEnabled(false);
			btnexport.setPreferredSize(dimension);
			btnexport.setActionCommand("btnexport");
			btnexport.setFont(new Font("新宋体", Font.PLAIN, 12));
			btnexport.addActionListener(this);

			reportMobile = new JButton("report");
			reportMobile.setEnabled(false);
			reportMobile.setPreferredSize(dimension);
			reportMobile.setActionCommand("reportMobile");
			reportMobile.setFont(new Font("新宋体", Font.PLAIN, 12));
			reportMobile.addActionListener(this);

			//rowPanel.add(mobLabel);
			//rowPanel.add(btnexportLog);
			//rowPanel.add(btnexport);
			//rowPanel.add(reportMobile);

			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			rowPanel.add(bar);
			rowPanel.add(runLabel);
			toolBarPanel.add(rowPanel);

			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			upButton = new JButton("move up");
			upButton.setActionCommand("up_btn");
			upButton.addActionListener(this);
			upButton.setPreferredSize(dimension);

			downButton = new JButton("move down");
			downButton.setActionCommand("down_btn");
			downButton.addActionListener(this);
			downButton.setPreferredSize(dimension);
			/*rowPanel.add(upButton);
			rowPanel.add(downButton);
			toolBarPanel.add(rowPanel);
			toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);
			 */
			toolBarPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);
			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			JLabel rowLabel = new JLabel("全选:");
			selectAllSP = new JRadioButton("选择全部  ");
			selectAllSP.setActionCommand("spselectAll");
			selectAllSP.addActionListener(this);
			//selectAllSP.setPreferredSize(dimension);
			JLabel logLabel = new JLabel("Log");
			logCheckBox = new JCheckBox();

			JButton terminate_btn = new JButton("terminate");
			terminate_btn.setActionCommand("terminate");
			terminate_btn.addActionListener(this);
			terminate_btn.setPreferredSize(dimension);

			rowPanel.add(rowLabel);
			rowPanel.add(selectAllSP);
			rowPanel.add(logLabel);
			rowPanel.add(logCheckBox);
			rowPanel.add(terminate_btn);

			toolBarPanel.add(rowPanel);

			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			JLabel filter_labl = new JLabel("过滤:");
			tf_filter_path = new JTextField();
			tf_filter_path.setFont(new Font("新宋体", Font.PLAIN, 12));
			tf_filter_path.setPreferredSize(filterdimension);
			GridBagConstraints gbc_tf_filter_path = new GridBagConstraints();
			gbc_tf_filter_path.insets = new Insets(0, 0, 0, 0);
			gbc_tf_filter_path.fill = GridBagConstraints.BOTH;
			JButton sure_btn = new JButton("确定");
			sure_btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					start_doing();
					Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							scriptTableModel.filter(tf_filter_path.getText());
							scriptTable.updateUI();
							stop_doing();
							JOptionPane.showMessageDialog(new JFrame(), "完成","提示", JOptionPane.INFORMATION_MESSAGE);
						}
					});
					thread.start();
				}
			});
			sure_btn.setPreferredSize(dimension);
			rowPanel.add(filter_labl);
			rowPanel.add(tf_filter_path, gbc_tf_filter_path);
			rowPanel.add(sure_btn);
			toolBarPanel.add(rowPanel);
		}
		return toolBarPanel;
	}
	private JPanel UiPanel() {
		if (spUiPanel == null) {
			spUiPanel = new scriptPanel(frame);
		}
		return spUiPanel;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JDialog ArgumentDialog(final int row){
		configDialog = new JDialog(frame, "参数配置 ", true){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected JRootPane createRootPane(){
				KeyStroke  stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
				JRootPane rootPane = new JRootPane();
				rootPane.registerKeyboardAction(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						configDialog.dispose();
					}
				},stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
				return rootPane;
			}
		};

		final RowBean rowBean_brfore = new RowBean();
		final RowBean rowBean_after = new RowBean();

		rowBean_brfore.setScript(tbpModel.getValueAt(row, 2).toString());
		rowBean_brfore.setSummary(tbpModel.getValueAt(row, 3).toString());
		rowBean_brfore.setArgument(tbpModel.getValueAt(row, 4).toString());
		rowBean_brfore.setDevice(tbpModel.getValueAt(row, 6).toString());
		rowBean_brfore.setSuitname(tbpModel.getValueAt(row, 7).toString());

		JPanel select_mk_panel = new JPanel();
		Dimension dimension = new Dimension(300, 20);
		Dimension ldimension = new Dimension(500, 20);
		Dimension lbDimension = new Dimension(60, 20);

		select_mk_panel.setLayout(new BoxLayout(select_mk_panel, BoxLayout.PAGE_AXIS));
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		final JComboBox<String> devjcombox = new JComboBox<String>();
		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel devLabel = new JLabel("设备");
		devLabel.setPreferredSize(lbDimension);
		String curItemDev = spTable.getValueAt(row, 6).toString();
		int rowcount = scriptTable.getRowCount();
		Set<String> devset=new HashSet<String>();
		for (int t = 0; t < rowcount; t++) {
			String dev = scriptTable.getValueAt(t, 4).toString();
			devset.add(dev);
		}
		devset.addAll(devicesList);

		for (String dev : devset) {
			devjcombox.addItem(dev);
		}

		devjcombox.setSelectedItem(curItemDev);

		rowPanel.add(devLabel);
		rowPanel.add(devjcombox);
		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel setLabel = new JLabel("集合");
		setLabel.setPreferredSize(lbDimension);
		String curItemSet = spTable.getValueAt(row, 7).toString();

		final JComboBox<String> setjcombox = new JComboBox<String>();
		setjcombox.setEnabled(false);
		final JTextField setjtField = new JTextField();
		setjtField.setEditable(false);
		setjtField.setPreferredSize(ldimension);

		Set<String> dset = allSet();
		for (String setValue : dset) {
			setjcombox.addItem(setValue);
		}
		setjcombox.setSelectedItem(curItemSet);
		setjtField.setText(curItemSet);
		setjcombox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				setjtField.setText(setjcombox.getSelectedItem().toString());
			}
		});

		rowPanel.add(setLabel);
		rowPanel.add(setjtField);
		rowPanel.add(setjcombox);
		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel spLabel = new JLabel("脚本");
		spLabel.setPreferredSize(lbDimension);
		final JTextField spJTextField = new JTextField();
		spJTextField.setEditable(false);
		spJTextField.setText(spTable.getValueAt(row, 2).toString());
		spJTextField.setPreferredSize(ldimension);

		rowPanel.add(spLabel);
		rowPanel.add(spJTextField);
		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);


		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel smLabel =new JLabel("描述");
		smLabel.setPreferredSize(lbDimension);
		final JTextField smJTextField = new JTextField();
		smJTextField.setEditable(false);
		smJTextField.setText(spTable.getValueAt(row, 3).toString());
		smJTextField.setPreferredSize(dimension);

		rowPanel.add(smLabel);
		rowPanel.add(smJTextField);
		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel eLabel =new JLabel("参数");
		eLabel.setPreferredSize(lbDimension);
		final JTextField eJTextField = new JTextField();
		eJTextField.setEditable(false);
		eJTextField.setText(spTable.getValueAt(row, 4).toString());
		eJTextField.setPreferredSize(ldimension);

		JCheckBox jCheckBox = new JCheckBox("修改");
		jCheckBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange()==ItemEvent.SELECTED) {
					spJTextField.setEditable(true);
					smJTextField.setEditable(true);
					eJTextField.setEditable(true);

					setjtField.setEditable(true);
					setjcombox.setEnabled(true);
				}else{
					spJTextField.setEditable(false);
					smJTextField.setEditable(false);
					eJTextField.setEditable(false);

					setjtField.setEditable(false);
					setjcombox.setEnabled(false);
				}

			}
		});
		JButton eButton = new JButton("Load");

		eButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String es= eJTextField.getText();
				if (es.contains("<")||es.contains(">")||es.contains("/")) {
					JOptionPane.showMessageDialog(new JFrame(), "参数值不能包含<,>,/","提示", JOptionPane.INFORMATION_MESSAGE);
				}else {

				}
				if(JOptionPane.showConfirmDialog(new JFrame(),"确定加载参数?", 
						"Warning",JOptionPane.YES_NO_OPTION) == 0){

					String[] cargs = es.split("\\|");
					for (String cs : cargs) {
						String[] csArg = cs.split("_");
						if (csArg.length==3) {
							ArgBean argBean = new ArgBean();
							argBean.setArgName(csArg[0]);
							argBean.setArgType(csArg[1]);
							argBean.setArgValue(csArg[2]);
							initValue(argBean);
							boolean repeat = false;
							String name = argBean.getArgName();
							for (int i = 0; i < argTable.getRowCount(); i++) {
								if (argTable.getValueAt(i, 1).toString().equals(name)) {
									repeat = true;
								}
							}
							if (repeat) {
								JOptionPane.showMessageDialog(new JFrame(),name+",已经存在,不能重复添加","提示", JOptionPane.INFORMATION_MESSAGE);
							}else {
								argModel.addRow(argBean.getArgName(), argBean.getArgType(), argBean.getArgValue());
								argTable.updateUI();
								argTable.setRowSelectionInterval(argModel.getRowCount()-1, argModel.getRowCount()-1);
							}
						}
					}
					JOptionPane.showMessageDialog(new JFrame(), "加载完成...","提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		rowPanel.add(eLabel);
		rowPanel.add(eJTextField);
		rowPanel.add(jCheckBox);
		rowPanel.add(eButton);
		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);


		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		JButton delbtn = new JButton("删除参数");
		delbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int  index = argTable.getSelectedRow();
				if (index!=-1) {
					if(JOptionPane.showConfirmDialog(new JFrame(),"确定删除参数?", 
							"Warning",JOptionPane.YES_NO_OPTION) == 0){
						Object selectObject = argModel.content.get(index);
						argModel.content.removeElement(selectObject);
						argModel.fireTableDataChanged();
						argTable.updateUI();
						argModel.serialize();

					}
				}
			}
		});
		JButton addbtn = new JButton("添加参数");

		rowPanel.add(delbtn);
		rowPanel.add(addbtn);
		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel ArgnameLbl= new JLabel("参数名字");
		ArgnameLbl.setPreferredSize(lbDimension);
		final JTextField e1AugTfd= new JTextField("NA");
		e1AugTfd.setPreferredSize(dimension);

		rowPanel.add(ArgnameLbl);
		rowPanel.add(e1AugTfd);
		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);


		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel ArgType= new JLabel("参数类型");
		ArgType.setPreferredSize(lbDimension);
		final JComboBox typejComboBox = new JComboBox();
		typejComboBox.addItem("FILE");
		typejComboBox.addItem("RANGE");
		typejComboBox.addItem("VALUE");
		typejComboBox.addItem("NA");

		typejComboBox.setSelectedIndex(3);


		rowPanel.add(ArgType);
		rowPanel.add(typejComboBox);
		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel ArgValue= new JLabel("参数值");
		ArgValue.setPreferredSize(lbDimension);

		final JTextField valueField = new JTextField();
		valueField.setPreferredSize(dimension);

		final NumberJTextField startRangeField = new NumberJTextField();
		startRangeField.setNumberOnly(true);
		startRangeField.setPreferredSize(lbDimension);

		final NumberJTextField endRangeField = new NumberJTextField();
		endRangeField.setNumberOnly(true);
		endRangeField.setPreferredSize(lbDimension);

		final JLabel serpLbl = new JLabel("-");

		final JButton selectBtn = new JButton("选择");
		selectBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = JarHelper.getJarProjectPath() + "\\data";
				File f = new File(path);
				if (!f.exists()) {
					f.mkdirs();
				}
				JFileChooser c = new JFileChooser(new File(path));
				c.setMultiSelectionEnabled(true);
				c.setFileSelectionMode(JFileChooser.FILES_ONLY);
				c.removeChoosableFileFilter(c.getAcceptAllFileFilter());
				c.setDialogTitle("请选择导入文件");
				FileNameExtensionFilter py = new FileNameExtensionFilter("txt(*.txt)", "txt");
				c.setFileFilter(py);
				int result = c.showOpenDialog(new JFrame());
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectFile = c.getSelectedFile();
					valueField.setText(selectFile.getAbsolutePath());
				}
			}
		});

		JButton argBtn = new JButton("确定修改参数");
		argBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = argTable.getSelectedRow();
				String argname;
				String argtype;
				String argvalue;
				String argStartRange;
				String argEndRange;
				System.out.println("确定修改参数:"+row);
				if (row!=-1) {
					int index = typejComboBox.getSelectedIndex();
					switch (index) {
					case 0:
						argname = e1AugTfd.getText();
						argtype = typejComboBox.getItemAt(index).toString();
						argvalue = valueField.getText();
						if (argname.trim().equals("")||argname.contains("_")) {
							JOptionPane.showMessageDialog(new JFrame(), "参数名不能为空或者包含'_'","提示", JOptionPane.INFORMATION_MESSAGE);
						}else {
							if (new File(argvalue).exists()) {
								argTable.setValueAt(argname, row, 1);
								argTable.setValueAt(argtype, row, 2);
								argTable.setValueAt(argvalue, row, 3);

								JOptionPane.showMessageDialog(new JFrame(), "设置成功","提示", JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(new JFrame(), "文件不存在","提示", JOptionPane.INFORMATION_MESSAGE);
							}

						}
						break;
					case 1:
						argname = e1AugTfd.getText();
						argtype = typejComboBox.getItemAt(index).toString();
						argStartRange = startRangeField.getText().trim();
						argEndRange = endRangeField.getText().trim();
						if (argname.trim().equals("")||argname.contains("_")) {
							JOptionPane.showMessageDialog(new JFrame(), "参数名不能为空或者包含字符 '_'","提示", JOptionPane.INFORMATION_MESSAGE);
						}else {
							if (argStartRange.trim().length()==0||argEndRange.trim().length()==0) {
								JOptionPane.showMessageDialog(new JFrame(), "参数值设置错误","提示", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (Long.parseLong(argStartRange)<=Long.parseLong(argEndRange)) {
									argTable.setValueAt(argname, row, 1);
									argTable.setValueAt(argtype, row, 2);
									argTable.setValueAt(String.format("%s-%s", argStartRange,argEndRange), row, 3);

									JOptionPane.showMessageDialog(new JFrame(), "设置成功","提示", JOptionPane.INFORMATION_MESSAGE);
								}else {
									JOptionPane.showMessageDialog(new JFrame(), "Range设置错误","提示", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
						break;
					case 2:
						argname = e1AugTfd.getText();
						argtype = typejComboBox.getItemAt(index).toString();
						argvalue = valueField.getText();
						if (argvalue.trim().length()==0) {
							JOptionPane.showMessageDialog(new JFrame(), "参数值不能为空","提示", JOptionPane.INFORMATION_MESSAGE);
						}else if (argname.trim().equals("")) {
							JOptionPane.showMessageDialog(new JFrame(), "参数名不能为空","提示", JOptionPane.INFORMATION_MESSAGE);
						}else {
							if (argname.trim().contains("_")||argvalue.contains("_")||argvalue.contains("<")||argvalue.contains(">")||argvalue.contains("|")) {
								JOptionPane.showMessageDialog(new JFrame(), "参数值不能包含_,<,>,|","提示", JOptionPane.INFORMATION_MESSAGE);
							}else {
								argTable.setValueAt(argname, row, 1);
								argTable.setValueAt(argtype, row, 2);
								argTable.setValueAt(argvalue.trim(), row, 3);
								JOptionPane.showMessageDialog(new JFrame(), "设置成功","提示", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						break;
					default:
						break;
					}
				}
			}
		});
		addbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String argname;
				String argtype;
				String argvalue;
				String argStartRange;
				String argEndRange;
				int index = typejComboBox.getSelectedIndex();
				switch (index) {
				case 0:
					argname = e1AugTfd.getText();
					argtype = typejComboBox.getItemAt(index).toString();
					argvalue = valueField.getText();
					if (argname.trim().equals("")||argname.contains("_")) {
						JOptionPane.showMessageDialog(new JFrame(), "参数名不能为空或者包含'_'","提示", JOptionPane.INFORMATION_MESSAGE);
					}else {
						if (new File(argvalue).exists()) {

							StringBuffer popstr = new StringBuffer();
							popstr.append("参数名字："+argname+"\n");
							popstr.append("参数类型："+argtype+"\n");
							popstr.append("参数值："+argvalue+"\n");

							boolean is_muti_argname = false;

							for (int i = 0; i < argModel.getRowCount(); i++) {
								Vector vector = (Vector)argModel.content.get(i);
								String vargname = vector.get(1).toString();
								if (argname.equals(vargname)) {
									is_muti_argname = true;
									break;
								}
							} 
							if (is_muti_argname) {
								JOptionPane.showMessageDialog(new JFrame(), "参数名字不能重复","提示", JOptionPane.WARNING_MESSAGE);
							}else {
								if(JOptionPane.showConfirmDialog(new JFrame(),"确定添加参数?\n"+popstr.toString(), 
										"Warning",JOptionPane.YES_NO_OPTION) == 0){
									argModel.addRow(argname, argtype, argvalue);
									argTable.updateUI();
									argTable.setRowSelectionInterval(argModel.getRowCount()-1, argModel.getRowCount()-1);

									JOptionPane.showMessageDialog(new JFrame(), "添加成功","提示", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}else {
							JOptionPane.showMessageDialog(new JFrame(), "文件不存在","提示", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					break;
				case 1:
					argname = e1AugTfd.getText();
					argtype = typejComboBox.getItemAt(index).toString();
					argStartRange = startRangeField.getText().trim();
					argEndRange = endRangeField.getText().trim();
					if (argname.trim().equals("")||argname.contains("_")) {
						JOptionPane.showMessageDialog(new JFrame(), "参数名不能为空或者包含字符 '_'","提示", JOptionPane.INFORMATION_MESSAGE);
					}else {
						if (argStartRange.trim().length()==0||argEndRange.trim().length()==0) {
							JOptionPane.showMessageDialog(new JFrame(), "参数值设置错误","提示", JOptionPane.INFORMATION_MESSAGE);
						}else {

							if (Long.parseLong(argStartRange)<=Long.parseLong(argEndRange)) {

								argvalue = String.format("%s-%s", argStartRange,argEndRange);

								StringBuffer popstr = new StringBuffer();
								popstr.append("参数名字："+argname+"\n");
								popstr.append("参数类型："+argtype+"\n");
								popstr.append("参数值："+argvalue+"\n");

								boolean is_muti_argname = false;
								for (int i = 0; i < argModel.getRowCount(); i++) {
									Vector vector = (Vector)argModel.content.get(i);
									String vargname = vector.get(1).toString();
									if (argname.equals(vargname)) {
										is_muti_argname = true;
										break;
									}
								} 
								if (is_muti_argname) {
									JOptionPane.showMessageDialog(new JFrame(), "参数名字不能重复","提示", JOptionPane.WARNING_MESSAGE);
								}else {
									if(JOptionPane.showConfirmDialog(new JFrame(),"确定添加参数?\n"+popstr.toString(), 
											"Warning",JOptionPane.YES_NO_OPTION) == 0){
										argModel.addRow(argname, argtype, argvalue);
										argTable.updateUI();
										argTable.setRowSelectionInterval(argModel.getRowCount()-1, argModel.getRowCount()-1);

										JOptionPane.showMessageDialog(new JFrame(), "添加成功","提示", JOptionPane.INFORMATION_MESSAGE);
									}
								}

							}else {
								JOptionPane.showMessageDialog(new JFrame(), "Range设置错误","提示", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					break;
				case 2:
					argname = e1AugTfd.getText();
					argtype = typejComboBox.getItemAt(index).toString();
					argvalue = valueField.getText();

					if (argvalue.trim().equals("")||argname.trim().equals("")||argname.contains("_")||argvalue.contains("_")) {
						JOptionPane.showMessageDialog(new JFrame(), "参数值,参数名不能为空或者包含'_'","提示", JOptionPane.INFORMATION_MESSAGE);
					}else {
						StringBuffer popstr = new StringBuffer();
						popstr.append("参数名字："+argname+"\n");
						popstr.append("参数类型："+argtype+"\n");
						popstr.append("参数值："+argvalue+"\n");

						boolean is_muti_argname = false;
						for (int i = 0; i < argModel.getRowCount(); i++) {
							Vector vector = (Vector)argModel.content.get(i);
							String vargname = vector.get(1).toString();
							if (argname.equals(vargname)) {
								is_muti_argname = true;
								break;
							}
						} 
						if (is_muti_argname) {
							JOptionPane.showMessageDialog(new JFrame(), "参数名字不能重复","提示", JOptionPane.WARNING_MESSAGE);
						}else {
							if(JOptionPane.showConfirmDialog(new JFrame(),"确定添加参数?\n"+popstr.toString(), 
									"Warning",JOptionPane.YES_NO_OPTION) == 0){
								argModel.addRow(argname, argtype, argvalue);
								argTable.updateUI();
								argTable.setRowSelectionInterval(argModel.getRowCount()-1, argModel.getRowCount()-1);

								JOptionPane.showMessageDialog(new JFrame(), "添加成功","提示", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}

					break;
				default:
					break;
				}
			}
		});
		rowPanel.add(ArgValue);
		rowPanel.add(valueField);
		rowPanel.add(startRangeField);
		rowPanel.add(serpLbl);
		rowPanel.add(endRangeField);

		rowPanel.add(selectBtn);
		rowPanel.add(argBtn);

		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		final JButton applybtn = new JButton("保存参数");
		rowPanel.add(applybtn);
		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		startRangeField.setVisible(false);
		serpLbl.setVisible(false);
		endRangeField.setVisible(false);
		selectBtn.setVisible(false);
		valueField.setVisible(false);

		typejComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int index = typejComboBox.getSelectedIndex();

				startRangeField.setVisible(true);
				serpLbl.setVisible(true);
				endRangeField.setVisible(true);
				selectBtn.setVisible(true);
				valueField.setVisible(true);

				switch (index) {
				case 0:
					startRangeField.setVisible(false);
					serpLbl.setVisible(false);
					endRangeField.setVisible(false);
					break;
				case 1:
					valueField.setVisible(false);
					selectBtn.setVisible(false);
					break;
				case 2:
					startRangeField.setVisible(false);
					serpLbl.setVisible(false);
					endRangeField.setVisible(false);
					selectBtn.setVisible(false);
					break;
				case 3:
					startRangeField.setVisible(false);
					serpLbl.setVisible(false);
					endRangeField.setVisible(false);
					selectBtn.setVisible(false);
					valueField.setVisible(false);
					break;
				default:
					break;
				}
			}
		});

		applybtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final int rowcnt = argTable.getRowCount();
				final String device = devjcombox.getSelectedItem().toString();
				final String script = spJTextField.getText();
				final String suitName = setjtField.getText();;
				final String summary = smJTextField.getText();
				if("".equals(script.trim())){
					JOptionPane.showMessageDialog(new JFrame(), "脚本不能为空!","提示", JOptionPane.INFORMATION_MESSAGE);
				}else if ("".equals(device.trim())) {
					JOptionPane.showMessageDialog(new JFrame(), "设备名不能为空!","提示", JOptionPane.INFORMATION_MESSAGE);
				}
				else if ("".equals(summary.trim())) {
					JOptionPane.showMessageDialog(new JFrame(), "描述不能为空!","提示", JOptionPane.INFORMATION_MESSAGE);
				}else if ("".equals(suitName.trim())) {
					JOptionPane.showMessageDialog(new JFrame(), "集合不能为空!","提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					if(JOptionPane.showConfirmDialog(new JFrame(),"确定保存设置?", 
							"Warning",JOptionPane.YES_NO_OPTION) == 0){

						configDialog.dispose();

						start_doing();

						//mainjTabbedPane.setSelectedIndex(1);

						Thread thread = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub

								spTable.getModel().removeTableModelListener(tableModelListener);

								String randomid = spTable.getValueAt(row, 5).toString();

								StringBuffer cmdArgumentbuBuffer = new StringBuffer();
								for (int i = 0; i < rowcnt; i++) {
									String argname = argTable.getValueAt(i, 1).toString();
									String argtype = argTable.getValueAt(i, 2).toString();
									String argvalue= argTable.getValueAt(i, 3).toString();
									if (i==rowcnt-1) {
										String cmd = String.format("%s_%s_%s", argname,argtype,argvalue);
										System.out.println(cmd);
										cmdArgumentbuBuffer.append(cmd);
									}else {
										String cmd = String.format("%s_%s_%s|", argname,argtype,argvalue);
										System.out.println(cmd);
										cmdArgumentbuBuffer.append(cmd);
									}
								}
								spTable.setValueAt(script, row, 2);
								spTable.setValueAt(summary, row, 3);
								spTable.setValueAt(suitName, row, 7);
								spTable.setValueAt(device, row, 6);

								if (rowcnt>=1) {
									spTable.setValueAt(cmdArgumentbuBuffer.toString(), row, 4);
									rowBean_after.setArgument(cmdArgumentbuBuffer.toString());
								}else {
									spTable.setValueAt("NA", row, 4);
									rowBean_after.setArgument("NA");
								}
								setTitle("start update config table");
								try {
									Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
									Table table = qdb.getTable("config");
									for (Row row : table) {
										if (randomid.equals(row.get("randomid"))) {
											row.put("device", device);
											row.put("script", script);
											row.put("summary", summary);
											row.put("suitName", suitName);
											row.put("es", cmdArgumentbuBuffer.toString());
											table.updateRow(row);
										}
									}
									qdb.close();
								} catch (Exception e2) {
									// TODO: handle exception
								}

								rowBean_after.setScript(script);
								rowBean_after.setSummary(summary);
								rowBean_after.setId_testsuit(randomid);
								rowBean_after.setDevice(device);
								rowBean_after.setSuitname(suitName);
								rowBean_after.setIsSelect(spTable.getValueAt(row,1).toString());
								rowBean_after.toString();

								if (rowBean_brfore.equals(rowBean_after)){
									Log.err("no data changes...");
								}else {
									if (rowBean_brfore.equalsDev(rowBean_after)) {
										Log.err("just device changed...");
										scriptTableModel.devChanged(randomid, device);
									}else {
										setTitle("start construct script table");
										scriptTableModel.construct(randomid, rowBean_after);
									}
								}
								setTitle("start construct script ui...");
								scriptTableModel.construct();
								scriptTable.updateUI();
								stop_doing();
								spTable.getModel().addTableModelListener(tableModelListener);
								JOptionPane.showMessageDialog(new JFrame(), "设置成功","提示", JOptionPane.INFORMATION_MESSAGE);
							}
						});
						thread.start();
					}
				}
			}
		});

		argModel = new ArgModel(20);
		argTable = new JTable(argModel){
			private static final long serialVersionUID = 1L;
		};
		argTable.getTableHeader().setReorderingAllowed(false);
		TableColumnModel tcm = argTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(10);
		tcm.getColumn(1).setPreferredWidth(30);
		tcm.getColumn(2).setPreferredWidth(30);
		tcm.getColumn(3).setPreferredWidth(500);
		argTable.setRowHeight(20);  
		argTable.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				/*int row = argTable.getSelectedRow();
				if (row!=-1) {
					System.out.println("select:"+row);
					if (row==argModel.getRowCount()) {
						e1AugTfd.setText("");
						typejComboBox.setSelectedItem("");
						String argtype =typejComboBox.getSelectedItem().toString();
						if (argtype.equals("RANGE")) {
							startRangeField.setText("");
							endRangeField.setText("");
						}else {
							valueField.setText("");
						}
					}else {
						String argname = argTable.getValueAt(row, 1).toString();
						String argtype = argTable.getValueAt(row, 2).toString();
						String argvalue = argTable.getValueAt(row, 3).toString();

						e1AugTfd.setText(argname);
						typejComboBox.setSelectedItem(argtype);
						if (argtype.equals("RANGE")) {
							startRangeField.setText(argvalue.split("\\-")[0]);
							endRangeField.setText(argvalue.split("\\-")[1]);
						}else {
							valueField.setText(argvalue);
						}
					}
				}*/
			}
		});
		argTable.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				int row = argTable.getSelectedRow();
				if (row!=-1) {
					System.out.println("select:"+row);
					String argname = argTable.getValueAt(row, 1).toString();
					String argtype = argTable.getValueAt(row, 2).toString();
					String argvalue = argTable.getValueAt(row, 3).toString();

					e1AugTfd.setText(argname);
					typejComboBox.setSelectedItem(argtype);
					if (argtype.equals("RANGE")) {
						startRangeField.setText(argvalue.split("\\-")[0]);
						endRangeField.setText(argvalue.split("\\-")[1]);
					}else {
						valueField.setText(argvalue);
					}

					/*startRangeField.setVisible(true);
					serpLbl.setVisible(true);
					endRangeField.setVisible(true);
					selectBtn.setVisible(true);
					valueField.setVisible(true);*/

				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
		constructArgumentTable(row);

		argTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = argTable.getSelectedRow();
				if (row!=-1) {
					System.out.println("select:"+row);
					String argname = argTable.getValueAt(row, 1).toString();
					String argtype = argTable.getValueAt(row, 2).toString();
					String argvalue = argTable.getValueAt(row, 3).toString();

					e1AugTfd.setText(argname);
					typejComboBox.setSelectedItem(argtype);
					if (argtype.equals("RANGE")) {
						startRangeField.setText(argvalue.split("\\-")[0]);
						endRangeField.setText(argvalue.split("\\-")[1]);
					}else {
						valueField.setText(argvalue);
					}

					/*startRangeField.setVisible(true);
					serpLbl.setVisible(true);
					endRangeField.setVisible(true);
					selectBtn.setVisible(true);
					valueField.setVisible(true);*/

				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JTableHeader tableHeader = spTable.getTableHeader();
		tableHeader.setReorderingAllowed(false);   //设置表格列不可重排
		DefaultTableCellRenderer hr =(DefaultTableCellRenderer)tableHeader.getDefaultRenderer();  //获得表格头的单元格对象
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);  //列名居中

		argTable.getTableHeader().setResizingAllowed(true);
		argTable.setRowSelectionAllowed(true);
		argTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );



		JScrollPane tablepan = new JScrollPane(argTable);
		((JComponent) configDialog.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		configDialog.setSize(800,500);
		configDialog.setLocationRelativeTo(frame);
		configDialog.getContentPane().add(tablepan,BorderLayout.CENTER);

		configDialog.getContentPane().add(select_mk_panel,BorderLayout.SOUTH);
		configDialog.setVisible(true);
		return configDialog;
	}
	public static boolean cmpRow(RowBean before,RowBean after){
		boolean need_redo = false;
		if (!before.getArgument().equals(after.getArgument())||
				!before.getDevice().equals(after.getDevice())||
				!before.getScript().equals(after.getScript())) {
			need_redo = true;
		}
		return  need_redo;
	}
	public static JDialog modifyDialog(){
		final JDialog modifyDialog = new JDialog(frame, "修改结果 ", true);
		JPanel select_md_panel = new JPanel();
		final JRadioButton _pass_btn=new JRadioButton("PASS");
		final JRadioButton _fail_btn=new JRadioButton("FAIL");
		final JRadioButton _notrun_btn=new JRadioButton("NOTRUN");
		final JRadioButton _term_btn=new JRadioButton("Terminated");

		_fail_btn.setEnabled(false);
		_notrun_btn.setEnabled(false);
		_term_btn.setEnabled(false);

		final ButtonGroup  buttonGroup=new ButtonGroup();
		buttonGroup.add(_pass_btn);
		buttonGroup.add(_fail_btn);
		buttonGroup.add(_notrun_btn);
		buttonGroup.add(_term_btn);
		JButton _ok_btn = new JButton("OK");
		_ok_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(new JFrame(),"确定?", 
						"Warning",JOptionPane.YES_NO_OPTION) == 0){
					modifyDialog.dispose();
					start_doing();
					Thread thread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (_pass_btn.isSelected()) {
								ArrayList<Integer>  srows = scriptTableModel.getSelectRows();
								for (Integer row : srows) {
									scriptTableModel.modifyRow(row, "PASS");
								}
							}
							if (_fail_btn.isSelected()) {
								ArrayList<Integer>  srows = scriptTableModel.getSelectRows();
								for (Integer row : srows) {
									scriptTableModel.modifyRow(row, "FAIL");
								}
							}
							if (_notrun_btn.isSelected()) {
								ArrayList<Integer>  srows = scriptTableModel.getSelectRows();
								for (Integer row : srows) {
									scriptTableModel.modifyRow(row, "NOTRUN");
								}
							}
							if (_term_btn.isSelected()) {
								ArrayList<Integer>  srows = scriptTableModel.getSelectRows();
								for (Integer row : srows) {
									scriptTableModel.modifyRow(row, "Terminated");
								}
							}
							scriptTable.updateUI();
							stop_doing();
						}
					});
					thread.start();
				}
			}
		});

		_ok_btn.setPreferredSize(dimension);
		select_md_panel.setLayout(new BoxLayout(select_md_panel, BoxLayout.PAGE_AXIS));
		select_md_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);
		JPanel rowPanel = new JPanel();
		rowPanel.add(_pass_btn);
		rowPanel.add(_fail_btn);
		rowPanel.add(_notrun_btn);
		rowPanel.add(_term_btn);
		select_md_panel.add(rowPanel);
		select_md_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		rowPanel = new JPanel();
		rowPanel.add(_ok_btn);
		select_md_panel.add(rowPanel);

		modifyDialog.setSize(400,100);
		modifyDialog.setResizable(false);
		modifyDialog.setLocationRelativeTo(frame);
		modifyDialog.getContentPane().add(select_md_panel,BorderLayout.SOUTH);
		modifyDialog.setVisible(true);
		return modifyDialog;
	}
	public static JDialog MobileDialog(String dir){
		mobileLogDialog = new JDialog(frame, "选择日志 ", true);

		JPanel select_mk_panel = new JPanel();
		Dimension dimension = new Dimension(300, 20);
		Dimension lbDimension = new Dimension(120, 20);

		select_mk_panel.setLayout(new BoxLayout(select_mk_panel, BoxLayout.PAGE_AXIS));
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		JPanel rowPanel = new JPanel();

		JButton rptbtn = new JButton("生成测试报告");
		rptbtn.setPreferredSize(lbDimension);
		rptbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> sList = mobileModel.getSelectRowsPath();
				if (sList.size()<=0) {
					JOptionPane.showMessageDialog(new JFrame(), "没有选中文件夹","提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					if(JOptionPane.showConfirmDialog(new JFrame(),"确定生成测试报告?", 
							"Warning",JOptionPane.YES_NO_OPTION) == 0){
						mobileLogDialog.dispose();
						try {
							String fileName  = JarHelper.getProjectPath()+"report\\" +System.currentTimeMillis()+"_stable_report.html";

							HtmlFreemarker.stableReport(sList,fileName);
							Log.info(fileName);

							Process process = Runtime.getRuntime().exec("cmd.exe /c start "+fileName);
							process.waitFor();

							//Runtime.getRuntime().exec("cmd.exe   /c   start "+fileName);
							JOptionPane.showMessageDialog(new JFrame(), "SUCCESS\n"+fileName,"提示", JOptionPane.INFORMATION_MESSAGE);
							//ServiceHelper.openResult(fileName);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				Log.info(sList.toString());
			}
		});

		JButton delBtn = new JButton("删除");
		delBtn.setPreferredSize(lbDimension);
		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Integer> sList = mobileModel.getSelectRows();
				if (sList.size()>=1) {
					if(JOptionPane.showConfirmDialog(new JFrame(),"确定删除选中的文件夹?", 
							"Warning",JOptionPane.YES_NO_OPTION) == 0){
						for (Integer i : sList) {
							String folder = JarHelper.getProjectPath()+"mobile_log";
							String folderName = mbLogTable.getValueAt(i,2).toString();
							String folderFile = String.format("%s\\%s", folder,folderName);
							Log.info("delete:"+folderFile);
							ServiceHelper.deleteDirectory(folderFile);
						}

						ArrayList<Object> sListcot = new ArrayList<Object>();

						for (Integer i : sList) {
							sListcot.add(mobileModel.content.get(i));
						}
						for (Object o : sListcot) {
							mobileModel.content.removeElement(o);
							mobileModel.fireTableDataChanged();
							mbLogTable.updateUI();
							mobileModel.serialize();
						}
					}
				}else {
					JOptionPane.showMessageDialog(new JFrame(), "没有选中文件夹","提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		rowPanel.add(rptbtn);
		rowPanel.add(delBtn);
		select_mk_panel.add(rowPanel);
		select_mk_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		mobileModel = new MobileModel(20);
		mbLogTable = new JTable(mobileModel){
			private static final long serialVersionUID = 1L;
		};
		mbLogTable.getTableHeader().setReorderingAllowed(false);
		TableColumnModel tcm = mbLogTable.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(10);
		tcm.getColumn(1).setPreferredWidth(30);
		tcm.getColumn(2).setPreferredWidth(200);
		mbLogTable.setRowHeight(20);  

		String folder = JarHelper.getProjectPath()+"mobile_log";
		ArrayList<String> folders = ServiceHelper.getFolderList(folder);
		for (String logFolder : folders) {
			mobileModel.addRow(false,logFolder);
		}

		for (int i = 0; i < mobileModel.getRowCount(); i++) {
			String fold_dir = mobileModel.getValueAt(i, 2).toString();
			if (dir.equals(fold_dir)) {
				mobileModel.setValueAt(true, i, 1);
				break;
			}
		}

		JTableHeader tableHeader = mbLogTable.getTableHeader();
		tableHeader.setReorderingAllowed(false);   //设置表格列不可重排
		DefaultTableCellRenderer hr =(DefaultTableCellRenderer)tableHeader.getDefaultRenderer();  //获得表格头的单元格对象
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);  //列名居中

		mbLogTable.getTableHeader().setResizingAllowed(true);
		mbLogTable.setRowSelectionAllowed(true);
		mbLogTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );

		JScrollPane tablepan = new JScrollPane(mbLogTable);
		((JComponent) mobileLogDialog.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		mobileLogDialog.setSize(400,300);
		mobileLogDialog.setLocationRelativeTo(frame);
		mobileLogDialog.getContentPane().add(tablepan,BorderLayout.CENTER);

		mobileLogDialog.getContentPane().add(select_mk_panel,BorderLayout.SOUTH);
		mobileLogDialog.setVisible(true);
		return mobileLogDialog;
	}
	private static Set<String> allSet(){
		Set<String> sets = new HashSet<String>();
		int count = spTable.getRowCount();
		if (count>=1) {
			for (int i = 0; i < count; i++) {
				sets.add(spTable.getValueAt(i, 7).toString());
			}
		}
		return sets;
	}
	public static JPanel AddScriptPanel(){
		JPanel select_add_panel = new JPanel();
		Dimension dimension = new Dimension(300, 20);
		Dimension cmdDim = new Dimension(120, 20);
		Dimension lbDimension = new Dimension(80, 20);

		select_add_panel.setLayout(new BoxLayout(select_add_panel, BoxLayout.PAGE_AXIS));
		select_add_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		JLabel devLbl= new JLabel("UIAutomator");
		devLbl.setPreferredSize(lbDimension);

		JLabel devLbl1= new JLabel("设备");
		devLbl1.setPreferredSize(lbDimension);

		final JTextField devtfd = new JTextField();
		devtfd.setPreferredSize(dimension);

		adddevjcombox = new JComboBox<String>();
		//devjcombox.addItem("HIKE 838");
		for (String dev : devicesList) {
			adddevjcombox.addItem(dev);
		}
		adddevjcombox.setPreferredSize(lbDimension);

		adddevjcombox.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					String dev = adddevjcombox.getSelectedItem().toString();
					if (dev!=null &&dev.trim().length()>=1) {
						devtfd.setText(dev);
					}
				} catch (Exception NullPointerException) {
					// TODO: handle exception
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		adddevjcombox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				devtfd.setText(adddevjcombox.getSelectedItem().toString());
			}
		});

		rowPanel.add(devLbl);
		rowPanel.add(devLbl1);
		rowPanel.add(devtfd);
		rowPanel.add(adddevjcombox);
		select_add_panel.add(rowPanel);


		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		JLabel setLbl= new JLabel("UIAutomator");
		setLbl.setPreferredSize(lbDimension);

		JLabel sLbl1= new JLabel("集合");
		sLbl1.setPreferredSize(lbDimension);

		final JTextField settfd = new JTextField();
		settfd.setPreferredSize(dimension);

		addsetcombox = new JComboBox<String>();
		Set<String> dSet = allSet();
		for (String set :dSet ) {
			addsetcombox.addItem(set);
		}
		addsetcombox.setPreferredSize(lbDimension);

		addsetcombox.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					String set = addsetcombox.getSelectedItem().toString();
					if (set!=null &&set.trim().length()>=1) {
						settfd.setText(set);
					}
				} catch (Exception NullPointerException) {
					// TODO: handle exception
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		addsetcombox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				settfd.setText(addsetcombox.getSelectedItem().toString());
			}
		});

		rowPanel.add(setLbl);
		rowPanel.add(sLbl1);
		rowPanel.add(settfd);
		rowPanel.add(addsetcombox);
		select_add_panel.add(rowPanel);


		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		JLabel cmdLblsm= new JLabel("UIAutomator");
		cmdLblsm.setPreferredSize(lbDimension);

		JLabel cmdLbsm1= new JLabel("描述");
		cmdLbsm1.setPreferredSize(lbDimension);

		final JTextField smjtfd = new JTextField();
		smjtfd.setPreferredSize(dimension);

		rowPanel.add(cmdLblsm);
		rowPanel.add(cmdLbsm1);
		rowPanel.add(smjtfd);
		select_add_panel.add(rowPanel);

		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		JLabel cmdLbl1= new JLabel("UIAutomator");
		cmdLbl1.setPreferredSize(lbDimension);

		JLabel cmdLbl2= new JLabel("脚本命令");
		cmdLbl2.setPreferredSize(lbDimension);


		final JTextField cmdjtfd = new JTextField();
		cmdjtfd.setPreferredSize(dimension);

		rowPanel.add(cmdLbl1);
		rowPanel.add(cmdLbl2);
		rowPanel.add(cmdjtfd);
		select_add_panel.add(rowPanel);


		rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel setLbl1= new JLabel("    ");
		setLbl1.setPreferredSize(cmdDim);
		JLabel setLbl2= new JLabel("    ");
		setLbl2.setPreferredSize(cmdDim);

		JButton setbtn = new JButton("设置");
		setbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						// TODO Auto-generated method stub
						String cmd = cmdjtfd.getText();
						String device = devtfd.getText();
						String summary = smjtfd.getText();
						String suitName = settfd.getText();
						String randomid = System.currentTimeMillis()+"";
						boolean TAG = true;
						if (cmd.contains("$")||device.contains("$")||summary.contains("$")) {
							TAG = false;
						}else if (cmd.contains("<")||device.contains("<")||summary.contains("<")) {
							TAG = false;
						}else if (cmd.contains(">")||device.contains(">")||summary.contains(">")) {
							TAG = false;
						}else {

						}
						if (TAG) {
							if (cmd.trim().length()==0) {
								JOptionPane.showMessageDialog(new JFrame(), "命令参数不能为空","提示", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if(JOptionPane.showConfirmDialog(new JFrame(),"确定添加脚本?", 
										"Warning",JOptionPane.YES_NO_OPTION) == 0){
									start_doing();

									spTable.getModel().removeTableModelListener(tableModelListener);
									if (device.trim().length()==0) {
										device= "NA";
									}
									if (summary.trim().length()==0) {
										summary= "NA";
									}
									try {
										Database db = new DatabaseBuilder(new File("Test.mdb")).open();
										Table table = db.getTable("config");
										Object[] row = new Object[table.getColumnCount()];
										row[0] = "false";
										row[1] =cmd;
										row[2] =summary;
										row[3] =device;
										row[4] =randomid;
										row[5] ="NA";
										row[6] =suitName;
										table.addRow(row);
										db.close();

										tbpModel.addRow(false, cmd, summary, "NA", randomid,device,suitName);
										spDialog.dispose();

										scriptTableModel.addRow(row);
										scriptTableModel.construct();
										scriptTable.updateUI();
										write_script_account();

									} catch (IOException es) {
										// TODO Auto-generated catch block
										es.printStackTrace();
									}

									//construct_log_table();

									spTable.getModel().addTableModelListener(tableModelListener);

									stop_doing();

									JOptionPane.showMessageDialog(new JFrame(), "添加成功","提示", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}else {
							JOptionPane.showMessageDialog(new JFrame(), "参数值不能包含字符【<,>,$】","提示", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				thread.start();
			}
		});
		setbtn.setPreferredSize(cmdDim);
		rowPanel.add(setLbl1);
		rowPanel.add(setLbl2);
		rowPanel.add(setbtn);
		select_add_panel.add(rowPanel);

		select_add_panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

		return select_add_panel;
	}

	private JPanel getkpiToolPanel() {
		if (toolkpiPanel == null) {
			toolkpiPanel = new JPanel();
			toolkpiPanel.setLayout(new BoxLayout(toolkpiPanel, BoxLayout.PAGE_AXIS));
			toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

			JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));


			JLabel rowLabel = new JLabel("列高:");
			final JComboBox<Integer> rowhBox = new JComboBox<Integer>();
			rowhBox.setPreferredSize(dimension);
			for (int i = 0; i < 20; i++) {
				rowhBox.addItem(20+i*5);
			}
			rowhBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					int rowHeight = Integer.parseInt(rowhBox.getSelectedItem().toString());
					System.out.println(rowHeight);
					spTable.setRowHeight(rowHeight);
					spTable.repaint();
				}
			});

			rowPanel.add(rowLabel);
			rowPanel.add(rowhBox);
			toolkpiPanel.add(rowPanel);
			toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

			scrpitsAdd = new JButton("add");
			scrpitsAdd.setActionCommand("scrpitsAdd");
			scrpitsAdd.setPreferredSize(dimension);
			scrpitsAdd.addActionListener(this);

			scrpitsImport = new JButton("import");
			scrpitsImport.setActionCommand("scrpitsImport");
			scrpitsImport.setPreferredSize(dimension);
			scrpitsImport.addActionListener(this);

			scrpitsDel = new JButton("delete");
			scrpitsDel.setActionCommand("scrpitsDel");
			scrpitsDel.addActionListener(this);
			scrpitsDel.setPreferredSize(dimension);
			
			script_device = new JButton("script_device");
			script_device.setActionCommand("script_device");
			script_device.addActionListener(this);
			script_device.setPreferredSize(dimension);
			script_device.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(JOptionPane.showConfirmDialog(new JFrame(),"确定修改所有的设备?", 
							"Warning",JOptionPane.YES_NO_OPTION) == 0){

						start_doing();
						Thread thread = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								String device = ServiceHelper.getDevice().get(0).get("name");
								scriptTableModel.devChangeAll(device);
								
								tbpModel.construct();
								spTable.updateUI();

								scriptTableModel.construct();
								scriptTable.updateUI();

								write_script_account();
								write_select_script_count();

								stop_doing();
								JOptionPane.showMessageDialog(new JFrame(), "操作完成！\n","提示", JOptionPane.INFORMATION_MESSAGE);
							}
						});
						thread.start();
					}
				}
			});

			scrpitsExport = new JButton("export");
			scrpitsExport.setActionCommand("scrpitsExport");
			scrpitsExport.addActionListener(this);
			scrpitsExport.setPreferredSize(dimension);

			toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);
			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			constantscript = new JButton("modify");
			constantscript.setPreferredSize(dimension);
			constantscript.setActionCommand("constantscript");
			//constantscript.setFont(new Font("新宋体", Font.PLAIN, 12));
			constantscript.addActionListener(this);


			rowPanel.add(scrpitsImport);
			rowPanel.add(scrpitsExport);
			rowPanel.add(script_device);
			toolkpiPanel.add(rowPanel);
			toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);


			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			rowPanel.add(scrpitsAdd);
			rowPanel.add(scrpitsDel);
			rowPanel.add(constantscript);
			toolkpiPanel.add(rowPanel);
			toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);


			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

			spUpButton = new JButton("move up");
			spUpButton.setPreferredSize(dimension);
			spUpButton.setActionCommand("spUpButton");
			spUpButton.setFont(new Font("新宋体", Font.PLAIN, 12));
			spUpButton.addActionListener(this);

			spDownButton = new JButton("move down");
			spDownButton.setPreferredSize(dimension);
			spDownButton.setActionCommand("spDownButton");
			spDownButton.setFont(new Font("新宋体", Font.PLAIN, 12));
			spDownButton.addActionListener(this);

			rowPanel.add(spUpButton);
			rowPanel.add(spDownButton);
			toolkpiPanel.add(rowPanel);
			toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			rowLabel = new JLabel("全选:");
			selectAll = new JRadioButton("选择全部");
			selectAll.setActionCommand("selectAll");

			selectAll.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					Thread t1 = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							int rowcount = spTable.getRowCount();
							// TODO Auto-generated method stub
							Database qdb;
							Table table;
							Table log_table;

							start_doing();

							if (rowcount>=1) {
								boolean flag = Boolean.parseBoolean(spTable.getValueAt(0, 1).toString());
								spTable.getModel().removeTableModelListener(tableModelListener);
								if (flag==true) {
									try {
										setTitle("start update config table");
										qdb = new DatabaseBuilder(new File("Test.mdb")).open();
										table = qdb.getTable("config");
										for (Row row : table) {
											row.put("isSelect", "false");
											table.updateRow(row);
										}
										qdb.close();

										setTitle("start update table ui...");
										for (int i = 0; i < spTable.getRowCount(); i++) {
											spTable.setValueAt(false, i, 1);
										}
										setTitle("start update log table");
										qdb = new DatabaseBuilder(new File("Test.mdb")).open();
										log_table = qdb.getTable("log");
										for (Row lrow : log_table) {
											lrow.put("isSelect", "false");
											log_table.updateRow(lrow);
										}
										qdb.close();

										scriptTableModel.construct();
										scriptTable.updateUI();

										write_script_account();
										write_select_script_count();

									} catch (Exception e2) {
										// TODO: handle exception
									}finally{
										stop_doing();
									}

								}else {
									try {
										qdb = new DatabaseBuilder(new File("Test.mdb")).open();
										table = qdb.getTable("config");
										for (Row row : table) {
											row.put("isSelect", "true");
											table.updateRow(row);
										}
										qdb.close();

										for (int i = 0; i < spTable.getRowCount(); i++) {
											spTable.setValueAt(true, i, 1);
										}

										qdb = new DatabaseBuilder(new File("Test.mdb")).open();
										log_table = qdb.getTable("log");
										for (Row lrow : log_table) {
											lrow.put("isSelect", "true");
											log_table.updateRow(lrow);
										}
										qdb.close();

										scriptTableModel.construct();
										scriptTable.updateUI();
										write_script_account();
										write_select_script_count();
									} catch (Exception e2) {
										// TODO: handle exception
									}finally{
										stop_doing();
									}
								}
								spTable.getModel().addTableModelListener(tableModelListener);
								JOptionPane.showMessageDialog(new JFrame(), "执行完成！", "提示",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					});
					t1.start();
				}
			});

			selectAll.setPreferredSize(dimension);
			rowPanel.add(rowLabel);
			rowPanel.add(selectAll);
			toolkpiPanel.add(rowPanel);
			toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);
			toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);


			rowPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			rowLabel = new JLabel("编辑:");

			editSelect= new JRadioButton("选择");
			editSelect.setActionCommand("editSelect");

			editSummary = new JRadioButton("描述");
			editSummary.setActionCommand("editSummary");

			editInteration = new JRadioButton("执行次数");
			editInteration.setActionCommand("editInteration");

			editAdjust = new JRadioButton("偏移量");
			editAdjust.setActionCommand("editAdjust");

			editKpi = new JRadioButton("标准值");
			editKpi.setActionCommand("editKpi");

			editStandard = new JRadioButton("备注");
			editStandard.setActionCommand("editStandard");

			JLabel scenno = new JLabel("      场景循环次数：");
			NumberJTextField numField = new NumberJTextField();
			numField.setNumberOnly(true);
			numField.setPreferredSize(lbDimension2);


			rowPanel.add(rowLabel);
			rowPanel.add(editSelect);
			rowPanel.add(editSummary);

			//rowPanel.add(scenno);
			//rowPanel.add(numField);
			//rowPanel.add(editInteration);
			//rowPanel.add(editAdjust);
			//rowPanel.add(editKpi);
			//rowPanel.add(editStandard);
			toolkpiPanel.add(rowPanel);

			toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);
			//toolkpiPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);

			//toolkpiPanel.setBackground(Color.lightGray);
		}
		return toolkpiPanel;
	}
	protected void processWindowEvent(WindowEvent e) {
		try {
			ServerSocket.CloseSocket();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(Lock.replay){
			JOptionPane.showMessageDialog(new JFrame(), "脚本在执行中，禁止退出！", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}else {
			int retcode = JOptionPane.showConfirmDialog(new JFrame(), "是否退出？","询问", JOptionPane.YES_NO_OPTION);
			System.out.println(retcode);
			if (retcode == 0) {
				System.exit(0);;
			} else {
				return; 
			}
		}
	}

	public JMenuBar getJMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			JMenu menu_file = new JMenu("文件");
			menu_file.setFont(new Font("新宋体", Font.PLAIN, 12));
			menuBar.add(menu_file);
			
			JMenuItem test=new JMenuItem("这里面没东西啦");
			test.setActionCommand("test");
			test.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			test.addActionListener(this);
			menu_file.add(test);

			JMenu menu_edit = new JMenu("编辑");
			menu_edit.setFont(new Font("新宋体", Font.PLAIN, 12));
			menuBar.add(menu_edit);

			JMenuItem clear = new JMenuItem("清空日志");
			clear.setActionCommand("clear");
			clear.setFont(new Font("新宋体", Font.PLAIN, 12));
			clear.addActionListener(this);
			menu_edit.add(clear);

			JMenu menu_setting = new JMenu("设置");
			menu_setting.setFont(new Font("新宋体", Font.PLAIN, 12));
			menuBar.add(menu_setting);

			JMenuItem setting = new JMenuItem("设置");
			setting.setActionCommand("setting");
			setting.setFont(new Font("新宋体", Font.PLAIN, 12));
			setting.addActionListener(this);
			menu_setting.add(setting);

			JMenu menu_help = new JMenu("帮助");
			menu_help.setFont(new Font("新宋体", Font.PLAIN, 12));
			menuBar.add(menu_help);

			JMenuItem help = new JMenuItem("帮助");
			help.setActionCommand("help");
			help.setFont(new Font("新宋体", Font.PLAIN, 12));
			help.addActionListener(this);
			menu_help.add(help);

			JMenuItem about = new JMenuItem("关于");
			about.setActionCommand("about");
			about.setFont(new Font("新宋体", Font.PLAIN, 12));
			about.addActionListener(this);
			menu_help.add(about);

		}
		return menuBar;
	}
	/**
	 * 初始化设置
	 */
	public static void main(String[] args) {

		init_ATT_Enviroment();
		init_Antbuild_xml();
		String lnf = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lnf);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Thread t1 = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							//start_doing();
							new MainFrame();
							write_select_script_count();
							write_script_account();
							//stop_doing();

							/*boolean TAG = ServiceHelper.isRootDevice();
							if (TAG==false) {
								JOptionPane.showMessageDialog(new JFrame(), "手机需要root权限","提示", JOptionPane.INFORMATION_MESSAGE);
							}*/
						} catch (Exception e) {

						}
					}
				});
				t1.start();
			}
		});
	}
	public void scriptAddRow(Vector<UiBean> addVecData,int index,String rowstr,String suitName){
		System.out.println(rowstr);
		String[] cmds = rowstr.split("\\$");
		String[] tpcmds = new String[4];

		int size = cmds.length;
		for(int i=0;i<4;i++) {
			if (i<size) {
				tpcmds[i] = cmds[i];
			}else {
				tpcmds[i] = "NA";
			}
		}
		String randomid = System.currentTimeMillis()+""+index;

		UiBean tcBean = new UiBean();
		tcBean.setSelected("false");
		tcBean.setScript(tpcmds[0]);
		tcBean.setDevice(tpcmds[1]);
		tcBean.setSummary(tpcmds[2]);
		String es = tpcmds[3];
		tcBean.setEs(es);
		tcBean.setRandomid(randomid);
		tcBean.setSuitName(suitName);
		addVecData.add(tcBean);

		tbpModel.addRow(false, tpcmds[0], tpcmds[2], tpcmds[3], randomid,tpcmds[1],suitName);

	}
	public ArrayList<Integer> getSelectdRows(){
		ArrayList<Integer> sArrayList = new ArrayList<Integer>();
		int rowCnt = spTable.getRowCount();
		for (int i = 0; i < rowCnt; i++) {
			boolean select = Boolean.parseBoolean(spTable.getValueAt(i, 1).toString());
			if (select) {
				sArrayList.add(i);
			}
		}
		//Log.info(sArrayList.toString());
		return sArrayList;
	}
	public static void stop_doing(){
		progressDialog.dispose();
	}
	public static void start_doing(){
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				start_doing_dialog();
			}
		}).start();
	}
	public static void setTitle(String title){
		if (progressDialog!=null) {
			progressDialog.setTitle(title);
		}
	}
	public static  JDialog start_doing_dialog(){
		progressDialog = new JDialog(frame,"操作在进行中,请等待...",true);
		progressDialog.setAlwaysOnTop(true);
		//progressDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL );  
		bar.setPreferredSize(dimension);
		bar.setToolTipText("执行中.....");
		bar.setPreferredSize(new Dimension(400, 20));
		bar.setMinimum(0);
		bar.setIndeterminate(true);
		progressDialog.setSize(500, 70);
		progressDialog.getContentPane().setLayout(new BorderLayout());
		JPanel jPanel = new JPanel();
		jPanel.add(bar);
		progressDialog.getContentPane().add(jPanel,BorderLayout.CENTER);
		progressDialog.setLocationRelativeTo(frame);
		progressDialog.setResizable(false);
		progressDialog.setVisible(true);
		return progressDialog;
	}
	public static JDialog AddScriptDialog(){

		spDialog = new JDialog(frame,"添加脚本"){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected JRootPane createRootPane(){
				KeyStroke  stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
				JRootPane rootPane = new JRootPane();
				rootPane.registerKeyboardAction(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						spDialog.dispose();
					}
				},stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
				return rootPane;
			}
		};
		((JComponent) spDialog.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		spDialog.setSize(620,300);
		spDialog.setLocationRelativeTo(frame);
		//mkrcaseDialog.getContentPane().add(scrollPane,BorderLayout.NORTH);
		spDialog.getContentPane().add(AddScriptPanel(),BorderLayout.NORTH);
		spDialog.setVisible(true);
		return spDialog;
	}
	public static  JDialog SettingDialog(final JFrame frm){
		final JDialog setDialog = new JDialog(frm, "Enviroment Setting", true);
		JLabel uLabel = new JLabel("Ant设置 :");
		final JTextField pathTF = new JTextField(40);
		String antLoc = SettingDb.readAntHome();
		pathTF.setText(antLoc);

		JButton brows = new JButton("Browse");
		JButton setButton = new JButton("set");
		setDialog.setSize(500, 70);
		setDialog.getContentPane().setLayout(new BorderLayout());
		JPanel jPanel = new JPanel();
		jPanel.add(uLabel);
		jPanel.add(pathTF);
		jPanel.add(brows);
		jPanel.add(setButton);
		brows.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setApproveButtonText("Cofirm");
				fileChooser.setDialogTitle("ant.bat");
				File file = new File(pathTF.getText());
				if (file.exists()) {
					fileChooser.setCurrentDirectory(file);
				}
				if(fileChooser.showOpenDialog(frm)==JFileChooser.APPROVE_OPTION){
					pathTF.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		setButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String mkpath = pathTF.getText();
				File mkbat = new File(mkpath);
				if (mkbat.exists()==true) {
					if(JOptionPane.showConfirmDialog(null,"Are you sure to apply setting?\n"+
							"Ant bat\n"+mkpath, 
							"Warning",JOptionPane.YES_NO_OPTION) == 0){
						SettingDb.setAntPath(mkpath);
						setDialog.dispose();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Ant.bat not exist \n"+mkpath, "Warning",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		setDialog.getContentPane().add(jPanel,BorderLayout.CENTER);
		setDialog.setLocationRelativeTo(frm);
		setDialog.setResizable(true);
		setDialog.setVisible(true);
		return setDialog;
	}
	private JDialog UIResultDialog(JFrame frm,LogBean caseBean) throws UnsupportedEncodingException{
		ResultDialog = new JDialog(frm, "UIAutomator Script Result", true){
			@Override
			protected JRootPane createRootPane(){
				KeyStroke  stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
				JRootPane rootPane = new JRootPane();
				rootPane.registerKeyboardAction(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ResultDialog.dispose();
					}
				},stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
				return rootPane;
			}
		};
		ResultDialog.setSize(850, 500);
		ResultDialog.getContentPane().setLayout(new BorderLayout());
		JPanel p1=new JPanel(); 
		p1.setLayout(new GridLayout(1,1)); 
		p1.setBorder(BorderFactory.createTitledBorder(caseBean.getRandomid()));
		JTextArea t1=new JTextArea(5,25); 
		t1.setTabSize(10); 
		t1.setLineWrap(true);
		t1.setWrapStyleWord(true);
		if (caseBean.getLogAugument()==null) {
			t1.setText("");
			p1.add(new JScrollPane(t1));
		}else {
			String str = caseBean.getLogAugument().replace("|BR|", "\n");
			t1.setText(str);
			p1.add(new JScrollPane(t1));
		}
		ResultDialog.getContentPane().add(p1,BorderLayout.CENTER);
		ResultDialog.setLocationRelativeTo(frm);
		ResultDialog.setResizable(true);
		ResultDialog.setVisible(true);

		return ResultDialog;
	}
	public String getSelectdRowsPop(){
		StringBuffer stringBuffer = new StringBuffer();
		int rowCnt = spTable.getRowCount();
		int selectCnt = getSelectdRows().size();
		if (selectCnt>=20) {
			stringBuffer.append(selectCnt+"项数据");
		}else {
			for (int i = 0; i < rowCnt; i++) {
				boolean select = Boolean.parseBoolean(spTable.getValueAt(i, 12).toString());
				if (select) {
					stringBuffer.append(spTable.getValueAt(i, 2).toString()+":");
					stringBuffer.append(spTable.getValueAt(i, 5).toString()+"\n");
				}
			}
		}
		return stringBuffer.toString();
	}
	public String checkRepeat(List<Hashtable<String, String>> list) {
		Hashtable<String, String> temp;
		boolean bool = false;
		String msg = null;
		for (int i = 0; i < list.size() - 1; i++) {
			if (bool) {
				break;
			}
			temp = list.get(i);
			for (int j = i + 1; j < list.size(); j++) {
				if (temp.get("name").equals(list.get(j).get("name"))) {
					msg = "存在多个名称为“" + temp.get("name") + "”的设备！";
					bool = true;
					break;
				}
			}
		}
		return msg;
	}
	public void sendCaseToDevice(){
		List<Hashtable<String, String>> devList = ServiceHelper.getDevice();
		ArrayList<String> errmsg = new ArrayList<String>();
		devname = new ArrayList<String>();
		if (devList == null) {
			deviceIsOk = false;
		} else 
			//jComboBox.removeAllItems();
			for (int i = 0; i < devList.size(); i++) {
				if (devList.get(i).get("status").equals("device")) {
					//System.out.println(devList.get(i).get("name"));
					String devitem = devList.get(i).get("name");
					devname.add(devitem);
				} else {
					errmsg.add(devList.get(i).get("name") + "\t" + devList.get(i).get("status"));
					Log.warn(devList.get(i).get("name") + "\t" + devList.get(i).get("status"));
				}
			}
		String msg = checkRepeat(devList);
		if(msg!=null){
			errmsg.add(msg);
		}
		String err = "";
		if(errmsg.size()>0){
			err=err+"警告：";
			for(int i=0;i<errmsg.size();i++){
				err=err+(i+1)+"、"+errmsg.get(i)+";";
			}
			deviceIsOk = false;
		}else{
			deviceIsOk = true;
		}
		List<String> cmdss = new ArrayList<String>();
		cmdss.add("adb shell mkdir /data/local/tmp/uiautomator/ ");
		Hashtable<String,Object> rett = ServiceHelper.RunCommand(cmdss);

		if (deviceIsOk&&devname.size()>=1) {
			if(JOptionPane.showConfirmDialog(new JFrame(),"确定保存文件到手机?\n", 
					"Warning",JOptionPane.YES_NO_OPTION) == 0){
				String dir= JarHelper.getProjectPath();
				if (!new File(dir).exists()) {
					new File(dir).mkdirs();
				}
				StringBuffer buffer= new StringBuffer();
				for (int i = 0; i < devname.size(); i++) {
					String devices = devname.get(i);
					String testcaseFile = String.format("%s%s", dir,"testcase.txt");
					List<String> cmds = new ArrayList<String>();
					cmds.add("adb push ");
					cmds.add(testcaseFile);
					cmds.add(" "+Constant.DIRPATH);

					buffer.append("device:"+devices+"\n");
					buffer.append(cmds.toString()+"\n");
					Hashtable<String,Object> ret = ServiceHelper.RunCommand(cmds);
					buffer.append(ret.get("msg").toString()+"\n");

					if (i == devname.size()-1) {
						JOptionPane.showMessageDialog(new JFrame(),buffer.toString() ,"提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "设备异常...","提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void pullScreen(){
		ArrayList<String> devList = devicesList;
		for (String dev : devList) {
			ArrayList<String> cmds = new ArrayList<String>();
			cmds.add("adb");
			cmds.add("-s");
			cmds.add(" \""+dev+"\" ");
			cmds.add("pull");
			cmds.add("/sdcard/CktTest/screen");
			cmds.add( JarHelper.getProjectPath()+"report"+File.separator+"screen");
			Hashtable<String,Object> ret = ServiceHelper.RunCommand(cmds);
		}
	}
	public void AntJunit(){
		ArrayList<Integer> slist = scriptTableModel.getSelectRows();
		if (slist.size()==0) {
			JOptionPane.showMessageDialog(new JFrame(), "没有选择脚本!","提示", JOptionPane.INFORMATION_MESSAGE);
		}else {
			if(JOptionPane.showConfirmDialog(new JFrame(),"确定生成测试报告?\n", 
					"Warning",JOptionPane.YES_NO_OPTION) == 0){
				//生成报告之前删除所有的xml文件
				String junitFolder =  JarHelper.getProjectPath()+"junit";
				File junitF= new File(junitFolder);
				if (!junitF.exists()) {
					junitF.mkdir();
				}else {
					File[] allFiles = junitF.listFiles();
					for (File file : allFiles) {
						if (file.getName().endsWith("xml")) {
							file.delete();
						}
					}
				}
				//生成xml数据文件
				try {
					JunitReader junitmy = new JunitReader();
					ArrayList<Testsuite> testsuites = junitmy.toReadTestsuites();
					for (Testsuite testsuite : testsuites) {
						try{
							JunitWriter myxml=new JunitWriter();
							myxml.toWrite(testsuite);
							myxml.toSave();
						}
						catch(ParserConfigurationException exp){
							exp.printStackTrace();
							System.out.print("Your writing is failed.");
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				//pull data
				Thread pullthread = new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						pullScreen();
					}
				});
				pullthread.start();

				//Ant build
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						//Ant build XML
						String buildFile = JarHelper.getJarProjectPath()+"build.xml";
						List<String> cmds = new ArrayList<String>();
						//String antLoc = EnvHelper.getEnv("ANT_HOME");
						String antLoc = SettingDb.readAntHome();
						String ant_bat = String.format("%s%s", antLoc,"\\bin\\ant.bat");
						cmds.add(ant_bat);
						cmds.add(" -buildfile ");
						cmds.add(buildFile);
						Log.info(cmds.toString());

						Hashtable<String,Object> ret = ServiceHelper.RunCommand(cmds);
						Object object = ret.get("code");
						String retstr = ret.get("msg").toString();
						if((Integer)object==0){
							if(retstr.contains("BUILD SUCCESSFUL")){
								System.out.println("报告编译成功！");
								Log.warn("...报告编译成功！...");
								SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
								String date = "Ant_"+df.format(new Date());

								String html = JarHelper.getJarProjectPath()+"html-report\\Junit\\junit-noframes.html";
								String renamehtml = JarHelper.getJarProjectPath()+"report\\"+date+".html";
								new File(html).renameTo(new File(renamehtml));
								try {
									Log.err("open html report file:"+renamehtml);
									Runtime.getRuntime().exec("cmd.exe   /c   start "+renamehtml);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								JOptionPane.showMessageDialog(new JFrame(), "报告编译成功!","提示", JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(new JFrame(), "报告编译失败!","警告", JOptionPane.WARNING_MESSAGE);
							}
						}else{
							System.out.println("=======");
							System.out.println("报告编译失败！");
							Log.err("...报告编译失败！");
							Log.err(ret.get("msg").toString());
							JOptionPane.showMessageDialog(frame, "报告编译失败!","警告", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				thread.start();
			}
		}
	}
	public void logThread(final String random){
		if (logCheckBox.isSelected()) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					Process logcatProcess = null;
					BufferedReader bufferedReader = null;
					String logcatDir = JarHelper.getProjectPath()+"\\logcat\\";
					if (!new File(logcatDir).exists()) {
						new File(logcatDir).mkdirs();
					}
					String logcatFile= JarHelper.getProjectPath()+"\\logcat\\"+random+".txt";
					ServiceHelper.writeToFile(logcatFile, "", true);
					try {
						stop=false;
						logcatProcess = Runtime.getRuntime().exec("adb logcat -v time");
						bufferedReader = new BufferedReader(new InputStreamReader(logcatProcess.getInputStream()));
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							//System.out.println(line);
							ServiceHelper.writeToFile(logcatFile, line, true);
							if (stop==true) {
								logcatProcess.destroy();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			t1.start();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("bt_up")) {
			int row = scriptTable.getSelectedRow();
			System.out.println("Row select : "+row);
			scriptTableModel.Upmodify(row);
			scriptTable.updateUI();
		}
		if (e.getActionCommand().equals("bt_down")) {
			int row = scriptTable.getSelectedRow();
			scriptTableModel.Downmodify(row);
			scriptTable.updateUI();
		}

		if (e.getActionCommand().equals("clear")) {
			if(JOptionPane.showConfirmDialog(new JFrame(),"确定要清除数据?", 
					"Warning",JOptionPane.YES_NO_OPTION) == 0){
				final ArrayList<Integer> slist = scriptTableModel.getSelectRows();
				try {
					Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
					Table  table = qdb.getTable("log");
					for (Row row : table) {
						String id_testcase = row.get("id_testcase").toString();
						for (Integer row2 : slist) {
							String random = scriptTableModel.getValueAt(row2, 7).toString();
							if (random.equals(id_testcase)) {
								row.put("result", "NOTRUN");
								row.put("LogArgument", "");
								table.updateRow(row);
								scriptTable.setValueAt("NOTRUN", row2, 6);
							}
						}
					}
					qdb.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				MainFrame.write_select_script_count();
				MainFrame.write_script_account();
				scriptTable.updateUI();
			}
		}
		if (e.getActionCommand().equals("random")) {
			if(JOptionPane.showConfirmDialog(new JFrame(),"确定要随机顺序?", 
					"Warning",JOptionPane.YES_NO_OPTION) == 0){
				int total = scriptTable.getRowCount();
				Hashtable<Integer,Integer> sequenceSet = RandomUtil.randSequence(total);
				scriptTableModel.sequenceTable(sequenceSet);
				scriptTable.updateUI();
			}
		}
		if (e.getActionCommand().equals("clear")) {
			scriptPanel.textPane.setText("");
		}
		if (e.getActionCommand().equals("reportMobile")){

			Hashtable<String, String> stable = scriptTableModel.get_Select_Row_Random_ID();
			String xml = JarHelper.getProjectPath()+"log\\log.xml";
			String dir = System.currentTimeMillis()+"";
			String folder = JarHelper.getProjectPath()+"mobile_log"+File.separator+dir+File.separator;
			LogXmlReader my = new LogXmlReader();
			final Vector<LogBean>  A = my.toRead(xml);

			for (String key : stable.keySet()) {
				int rowId = Integer.parseInt(key);
				String logFile = folder+File.separator+String.format("%03d", rowId)+".log";
				String randomid = stable.get(key);
				String summary = scriptTable.getValueAt(rowId,3).toString();
				String result ="";
				for (LogBean logBean : A) {
					if (randomid.equals(logBean.getRandomid())) {
						result ="SUMMARY:"+summary+"\n";
						result =result+logBean.getLogAugument();
						ServiceHelper.writeToFile2(logFile, result.replace("|BR|", "\n"), false);
						break;
					}
				}
			}
			StringBuffer devinfoBuffer = new StringBuffer();
			devinfoBuffer.append("IMEI:"+"IMEI"+"\n");
			devinfoBuffer.append("SoftWare:"+"SOFTWARE"+"\n");
			devinfoBuffer.append("hardware:"+"HARDWARE"+"\n");
			String dvinfo = folder+File.separator+"devinfo.txt";
			ServiceHelper.writeToFile2(dvinfo, devinfoBuffer.toString(), false);
			MobileDialog(dir);
		}
		if (e.getActionCommand().equals("btnexportLog")) {
			List<Hashtable<String, String>> devList = ServiceHelper.getDevice();
			ArrayList<String> errmsg = new ArrayList<String>();
			devname = new ArrayList<String>();
			if (devList == null) {
				deviceIsOk = false;
			} else 
				//jComboBox.removeAllItems();
				for (int i = 0; i < devList.size(); i++) {
					if (devList.get(i).get("status").equals("device")) {
						//System.out.println(devList.get(i).get("name"));
						String devitem = devList.get(i).get("name");
						devname.add(devitem);
					} else {
						errmsg.add(devList.get(i).get("name") + "\t" + devList.get(i).get("status"));
						Log.warn(devList.get(i).get("name") + "\t" + devList.get(i).get("status"));
					}
				}
			String msg = checkRepeat(devList);
			if(msg!=null){
				errmsg.add(msg);
			}
			String err = "";
			if(errmsg.size()>0){
				err=err+"警告：";
				for(int i=0;i<errmsg.size();i++){
					err=err+(i+1)+"、"+errmsg.get(i)+";";
				}
				deviceIsOk = false;
			}else{
				deviceIsOk = true;
			}

			if (deviceIsOk) {
				if(JOptionPane.showConfirmDialog(new JFrame(),"确定导出Log文件?\n", 
						"Warning",JOptionPane.YES_NO_OPTION) == 0){
					String LogFile= JarHelper.getProjectPath()+"mobile_log\\";
					if (!new File(LogFile).exists()) {
						new File(LogFile).mkdirs();
					}
					StringBuffer buffer= new StringBuffer();
					for (int i = 0; i < devname.size(); i++) {
						String devices = devname.get(i);
						SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
						String date = df.format(new Date());

						String folder = String.format("%s_%s", date,devices);
						String LogFileFolder = String.format("%s%s", LogFile,folder);
						new File(LogFileFolder).mkdirs();
						List<String> cmds = new ArrayList<String>();
						cmds.add("adb pull ");
						cmds.add(Constant.LOGPATH+" ");
						cmds.add("\""+LogFileFolder+"\"");

						buffer.append("device:"+devices+"\n");
						buffer.append(cmds.toString()+"\n");
						Hashtable<String,Object> ret = ServiceHelper.RunCommand(cmds);
						Log.info(ret.get("msg").toString());

						if (i == devname.size()-1) {
							JOptionPane.showMessageDialog(new JFrame(),"成功" ,"提示", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}else {
				String errorP = "";
				for (String error : devErrorList) {
					errorP = error+"\n";
				}
				JOptionPane.showMessageDialog(new JFrame(), errorP,"提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (e.getActionCommand().equals("btnexport")) {
			ArrayList<Integer> slist = scriptTableModel.getSelectRows();
			if (slist.size()==0) {
				JOptionPane.showMessageDialog(new JFrame(), "没有选择脚本!","提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				String FileName = JarHelper.getProjectPath()+"testcase.txt";

				String caseTime=JOptionPane.showInputDialog(null,"循环次数:","提示",JOptionPane.PLAIN_MESSAGE);
				//System.out.println(caseTime);
				if (caseTime==null) {

				}else {
					if (caseTime.matches("\\d+")) {
						if(JOptionPane.showConfirmDialog(new JFrame(),"确定导出脚本?\n"+FileName, 
								"Warning",JOptionPane.YES_NO_OPTION) == 0){

							StringBuffer stringBuffer = new StringBuffer();
							for (Integer row : slist) {
								String summary  = scriptTable.getValueAt(row, 3).toString();
								String script  = scriptTable.getValueAt(row, 2).toString();
								String argument  = scriptTable.getValueAt(row, 5).toString();
								String cmd  = String.format("%s %s $%s \n",script,argument,summary);
								stringBuffer.append(cmd);
								Log.info(cmd);
							}
							ServiceHelper.writeToFile(FileName, "", false);
							String caseTxt = stringBuffer.toString();
							int cycle = Integer.parseInt(caseTime);
							for (int j = 0; j < cycle; j++) {
								ServiceHelper.writeToFile(FileName,caseTxt , true);
							}
							sendCaseToDevice();
						}
					}else {
						JOptionPane.showMessageDialog(new JFrame(),"只能输入数字" ,"提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
		if (e.getActionCommand().equals("report")) {
			String time =String.valueOf(System.currentTimeMillis());
			String fileName = "ATT_"+time+".html";
			String reportFile = JarHelper.getProjectPath()+File.separator+"report"+File.separator+fileName;

			if(JOptionPane.showConfirmDialog(new JFrame(),"确定生成测试报告?\n"+reportFile, 
					"Warning",JOptionPane.YES_NO_OPTION) == 0){
				Report.export(reportFile,scriptTable);
			}
		}

		if (e.getActionCommand().equals("constantscript")) {
			int row= spTable.getSelectedRow();
			if (row==-1) {
				JOptionPane.showMessageDialog(new JFrame(), "没有选择脚本!","提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				ArgumentDialog(row);
			}
		}
		if (e.getActionCommand().equals("scrpitsExport")) {

			int rowcnt =  spTable.getRowCount();
			ArrayList<Integer> select_index = new ArrayList<Integer>();
			for (int i = 0; i < rowcnt; i++) {
				Boolean selected = Boolean.parseBoolean(spTable.getValueAt(i, 1).toString());
				if (selected) {
					select_index.add(i);
				}
			}
			if (select_index.size()>=1) {

				String path = JarHelper.getJarProjectPath() + "\\testsuit";
				File f = new File(path);
				if (!f.exists()) {
					f.mkdirs();
				}

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setApproveButtonText("确定");
				fileChooser.setDialogTitle("导出测试脚本...");
				FileNameExtensionFilter py = new FileNameExtensionFilter("txt(*.txt)", "txt");
				fileChooser.setFileFilter(py);
				File file = new File(path);
				if (file.exists()) {
					fileChooser.setCurrentDirectory(file);
				}
				if(fileChooser.showOpenDialog(new JFrame())==JFileChooser.APPROVE_OPTION){
					String name = fileChooser.getSelectedFile().getName();
					String filename = fileChooser.getSelectedFile().getAbsolutePath();
					if (name!=null) {
						name = name.trim();
						if ("".equals(name)) {
							JOptionPane.showMessageDialog(new JFrame(), "文件名字不能为空!","提示", JOptionPane.INFORMATION_MESSAGE);
						}else {
							if (filename.contains(".txt")) {

							}else {
								filename = filename+".txt";
							}
							StringBuffer sbBuffer = new StringBuffer();
							for (int i = 0; i < select_index.size(); i++) {
								Vector vector = (Vector)tbpModel.content.get(i);
								sbBuffer.append(String.format("%s$%s$%s$%s\n", vector.get(2),vector.get(6),vector.get(3),vector.get(4)));
							}  
							ServiceHelper.writeToFile(filename, sbBuffer.toString(), false);
							Log.info("export to file:"+filename);
						}
					}
				}
			}else {
				JOptionPane.showMessageDialog(new JFrame(), "没有选中脚本!","提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (e.getActionCommand().equals("scrpitsDel")) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {

					final Hashtable<String, Object> isselecthashtable = tbpModel.getSelectHash();;
					if (isselecthashtable.size()>=1) {
						if(JOptionPane.showConfirmDialog(new JFrame(),"确定删除选中的"+isselecthashtable.size()+"个脚本?", 
								"Warning",JOptionPane.YES_NO_OPTION) == 0){

							start_doing();

							Thread thread = new Thread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub

									for (String randomid : isselecthashtable.keySet()) {
										try {
											Database qdb = new DatabaseBuilder(new File("Test.mdb")).open();
											Table table = qdb.getTable("config");
											for (Row row : table) {
												if (randomid.equals(row.get("randomid"))) {
													table.deleteRow(row);
												}
											}
											qdb.close();
										} catch (Exception e2) {
											// TODO: handle exception
										}
									}
									for (String randomid : isselecthashtable.keySet()) {
										try {
											Database db = new DatabaseBuilder(new File("Test.mdb")).open();
											Table logTable = db.getTable("log");
											for (Row row : logTable) {
												if (randomid.equals(row.get("id_testsuit"))) {
													logTable.deleteRow(row);
												}
											}
											db.close();

										} catch (Exception e2) {
											// TODO: handle exception
										}
									}
									/*									for (String randomid : isselecthashtable.keySet()) {
										tbpModel.content.remove(isselecthashtable.get(randomid));
										spTable.updateUI();
									}
									 */									//tbpModel.serialize();

									tbpModel.construct();
									spTable.updateUI();

									scriptTableModel.construct();
									scriptTable.updateUI();

									write_script_account();
									write_select_script_count();

									stop_doing();
									JOptionPane.showMessageDialog(new JFrame(), "成功删除选中的"+isselecthashtable.size()+"个脚本!\n","提示", JOptionPane.INFORMATION_MESSAGE);
								}
							});
							thread.start();
						}
					}else {
						JOptionPane.showMessageDialog(new JFrame(), "没有选中脚本!","提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			thread.start();
		}
		if (e.getActionCommand().equals("replay")) {
			int devicecnt = scriptPanel.cb_device.getItemCount();
			final ArrayList<Integer> slist = scriptTableModel.getSelectRows();
			Log.info(slist.toString());

			File jarFile  = new File(scriptPanel.tf_file_path.getText());
			if(Lock.replay){
				JOptionPane.showMessageDialog(new JFrame(), "还有脚本正在执行，请稍等!","提示", JOptionPane.INFORMATION_MESSAGE);
			}else if (!jarFile.exists()) {
				JOptionPane.showMessageDialog(new JFrame(), "请选择Jar文件!","提示", JOptionPane.INFORMATION_MESSAGE);
			}else if (devicecnt==0) {
				JOptionPane.showMessageDialog(new JFrame(), "测试设备异常，请检查！", "提示",JOptionPane.INFORMATION_MESSAGE);
			}else if (slist.size()<=0) {
				JOptionPane.showMessageDialog(new JFrame(), "请选择脚本！", "提示",JOptionPane.INFORMATION_MESSAGE);
			}else{
				if(JOptionPane.showConfirmDialog(new JFrame(),"执行选中脚本?\n", 
						"Warning",JOptionPane.YES_NO_OPTION) == 0){
					bar.setIndeterminate(true);
					Lock.setUp();
					final String jarName = jarFile.getName();
					runthread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							int total = slist.size();
							int current = 1;
							for (Integer i : slist) {
								bar.setValue((100*current)/total);
								current = current+1;
								runLabel.setText(scriptTable.getValueAt(i, 3).toString());

								String uicmd = scriptTable.getValueAt(i, 2).toString();
								String device = scriptTable.getValueAt(i, 4).toString();
								String escmd = scriptTable.getValueAt(i, 5).toString();
								String status = scriptTable.getValueAt(i, 6).toString().toLowerCase();
								final String random = scriptTable.getValueAt(i, 7).toString();

								ArrayList<String> cmdlist = new ArrayList<String>();
								cmdlist.add("adb ");
								cmdlist.add("-s ");
								cmdlist.add("\""+device+"\"");
								cmdlist.add(" shell ");
								cmdlist.add("uiautomator runtest ");
								cmdlist.add(jarName+" ");
								cmdlist.add("-c");
								cmdlist.add(uicmd);

								cmdlist.add(" "+escmd);
								cmdlist.add(" -e RANDOM "+random);

								logThread(random);

								UIAutomatorThread rThread = new UIAutomatorThread(i,random,cmdlist,status);
								Thread runthread = new Thread(rThread);
								runthread.start();
								try {
									runthread.join();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							bar.setIndeterminate(false);
							runLabel.setText("完成");
							JOptionPane.showMessageDialog(new JFrame(), "执行完成!","提示", JOptionPane.INFORMATION_MESSAGE);
							Lock.tearDown();
							Lock.replay= false;

							stop =true;
							termiante=false;

							//add ant junit report
							AntJunit();
						}
					});
					runthread.start();
				}
			}
		}
		if (e.getActionCommand().equals("spselectAll")) {
			// TODO Auto-generated method stub
			final int rowcount = scriptTable.getRowCount();
			// TODO Auto-generated method stub
			if (rowcount>=1) {
				scriptTable.getModel().removeTableModelListener(scripttableModelListener);
				start_doing();
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						boolean flag = Boolean.parseBoolean(scriptTable.getValueAt(0, 1).toString());
						scriptTableModel.update_select_all(tf_filter_path.getText(),!flag);
						scriptTableModel.ui_select_all(!flag);
						//scriptTableModel.construct_select_all(tf_filter_path.getText(),!flag);
						MainFrame.scriptTable.updateUI();

						stop_doing();
						scriptTable.getModel().addTableModelListener(scripttableModelListener);
					}
				});
				thread.start();
			}
		}
		if (e.getActionCommand().equals("scrpitsAdd")) {
			AddScriptDialog();
		}

		if (e.getActionCommand().equals("spUpButton")) {
			final int row = spTable.getSelectedRow();
			final int count = spTable.getRowCount();
			if (row==-1) {
				JOptionPane.showMessageDialog(new JFrame(), "没有选中脚本!","提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				if (count==1) {

				}else {
					final String randomid = spTable.getValueAt(row, 5).toString();

					Thread thread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							tbpModel.removeTableModelListener(tableModelListener);

							start_doing();
							tbpModel.up(randomid);
							tbpModel.construct();
							scriptTableModel.updown();
							scriptTableModel.construct();

							spTable.updateUI();
							scriptTable.updateUI();
							if (row==0) {
								spTable.setRowSelectionInterval(count-1, count-1);
							}else{
								spTable.setRowSelectionInterval(row-1, row-1);
							}
							tbpModel.addTableModelListener(tableModelListener);
							stop_doing();
							JOptionPane.showMessageDialog(new JFrame(), "操作成功!","提示", JOptionPane.INFORMATION_MESSAGE);
						}
					});
					thread.start();
				}
			}
		}
		if (e.getActionCommand().equals("spDownButton")){
			final int row = spTable.getSelectedRow();
			final int count = spTable.getRowCount();
			if (row==-1) {
				JOptionPane.showMessageDialog(new JFrame(), "没有选中脚本!","提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				if (count==1) {

				}else {
					final String randomid = spTable.getValueAt(row, 5).toString();

					Thread thread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							tbpModel.removeTableModelListener(tableModelListener);

							start_doing();
							tbpModel.down(randomid);
							tbpModel.construct();
							scriptTableModel.updown();
							scriptTableModel.construct();

							spTable.updateUI();
							scriptTable.updateUI();
							if (row==count-1) {
								spTable.setRowSelectionInterval(0, 0);
							}else{
								spTable.setRowSelectionInterval(row+1, row+1);
							}
							tbpModel.addTableModelListener(tableModelListener);
							stop_doing();
							JOptionPane.showMessageDialog(new JFrame(), "操作成功!","提示", JOptionPane.INFORMATION_MESSAGE);
						}
					});
					thread.start();
				}
			}
		}
		if (e.getActionCommand().equals("AntJunit")) {
			AntJunit();
		}
		if (e.getActionCommand().equals("up_btn")) {
			int total = scriptTable.getRowCount();
			int row = scriptTable.getSelectedRow();
			if (total<=1) {
				JOptionPane.showMessageDialog(new JFrame(), "不能移动数据","提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				if (row==-1) {
					JOptionPane.showMessageDialog(new JFrame(), "没有选中数据","提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					scriptTableModel.Upmodify(row);
					scriptTable.updateUI();
				}
			}
		}
		if (e.getActionCommand().equals("terminate")) {
			if(JOptionPane.showConfirmDialog(new JFrame(),"确定终止执行脚本?", 
					"Warning",JOptionPane.YES_NO_OPTION) == 0){
				termiante = true;
				ServiceHelper.KillUiAutomatorPids(false);
				JOptionPane.showMessageDialog(new JFrame(), "成功终止执行脚本!","提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (e.getActionCommand().equals("setting")) {
			SettingDialog(new JFrame());
		}
		if (e.getActionCommand().equals("down_btn")) {
			int total = scriptTable.getRowCount();
			int row = scriptTable.getSelectedRow();
			if (total<=1) {
				JOptionPane.showMessageDialog(new JFrame(), "不能移动数据","提示", JOptionPane.INFORMATION_MESSAGE);
			}else {
				if (row==-1) {
					JOptionPane.showMessageDialog(new JFrame(), "没有选中数据","提示", JOptionPane.INFORMATION_MESSAGE);
				}else {
					scriptTableModel.Downmodify(row);
					scriptTable.updateUI();
				}
			}
		}
		if (e.getActionCommand().equals("scrpitsImport")) {
			String path = JarHelper.getJarProjectPath() + "\\testsuit";
			File f = new File(path);
			if (!f.exists()) {
				f.mkdirs();
			}
			JFileChooser c = new JFileChooser(new File(JarHelper.getJarProjectPath() + "\\testsuit"));
			c.setMultiSelectionEnabled(true);
			c.setFileSelectionMode(JFileChooser.FILES_ONLY);
			c.removeChoosableFileFilter(c.getAcceptAllFileFilter());
			c.setDialogTitle("请选择导入文件");
			FileNameExtensionFilter py = new FileNameExtensionFilter("txt(*.txt)", "txt");
			c.setFileFilter(py);

			int result = c.showOpenDialog(new JFrame());
			if (result == JFileChooser.APPROVE_OPTION) {

				//start_doing();
				final File selectFile = c.getSelectedFile();
				start_doing();
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						List<String> cases = TextFile.read(selectFile.getAbsolutePath());
						//System.out.println(cases.toString());
						Vector<UiBean> addVecData= new Vector<UiBean>();
						tbpModel.removeTableModelListener(tableModelListener);
						for (int i = 0; i < cases.size(); i++) {
							scriptAddRow(addVecData,i,cases.get(i),selectFile.getName().replace(".txt", ""));
						}
						try {
							for (UiBean uiBean : addVecData) {
								Database db = new DatabaseBuilder(new File("Test.mdb")).open();
								Table table = db.getTable("config");
								Object[] row = new Object[table.getColumnCount()];
								row[0] = uiBean.getSelected();
								row[1] =uiBean.getScript();
								row[2] =uiBean.getSummary();
								row[3] =uiBean.getDevice();
								row[4] =uiBean.getRandomid();
								row[5] =uiBean.getEs();
								row[6] =uiBean.getSuitName();
								table.addRow(row);
								db.close();
								construct_log_table(row);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						tbpModel.construct();
						tbpModel.addTableModelListener(tableModelListener);

						//construct_log_table();

						//scriptTableModel.construct();
						//scriptTable.updateUI();

						write_script_account();

						stop_doing();
						JOptionPane.showMessageDialog(new JFrame(), "导入成功\n","提示", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				thread.start();
			}else {

			}
		}
	}
}
