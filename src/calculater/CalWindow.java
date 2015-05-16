package calculater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class CalWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuBar bar;// 菜单栏
	private JMenu view;
	private JMenu edit;
	private JMenu help;
	private Icon markIcon = new ImageIcon(getClass().getClassLoader()
			.getResource("imgs/circle.png"));
	private JMenuItem[][] items = {
			{ new JMenuItem("标准型(T)"), new JMenuItem("科学型(S)"),
					new JMenuItem("程序员型(P)"), new JMenuItem("统计信息(A)"),
					new JMenuItem("."), new JMenuItem("历史记录(Y)"),
					new JMenuItem("数字分组(I)"), new JMenuItem("."),
					new JMenuItem("基本(B)"), new JMenuItem("单位转换(U)"),
					new JMenuItem("日期计算(D)"), new JMenu("工作表(W)") },
			{ new JMenuItem("复制(C)"), new JMenuItem("粘贴(P)"),
					new JMenuItem("."), new JMenu("历史记录(H)") },
			{ new JMenuItem("查看帮助(V)"), new JMenuItem("."),
					new JMenuItem("关于计算器(A)") },
			{ new JMenuItem("抵押(M)"), new JMenuItem("汽车租赁(V)"),
					new JMenuItem("油耗(mpg)(F)"),
					new JMenuItem("油耗(|/100km)(U)") },
			{ new JMenuItem("复制历史记录(I)"), new JMenuItem("编辑(E)"),
					new JMenuItem("取消编辑(N)"), new JMenuItem("清除(L)") } };
	private JPanel panelContainer;

	public CalWindow() {
		super("calculater");
		initMenuAndBar();
		initBasicPane();
		launchFrame();
	}


	private void initBasicPane() {
		// TODO Auto-generated method stub
		initItemsIcon();
		panelContainer = new StandardPane();
		add(panelContainer);
	}

	private void initItemsIcon() {
		items[0][0].setIcon(markIcon);
		items[0][8].setIcon(markIcon);
	}

	private void initMenuAndBar() {
		createViewMenu();
		createEditMenu();
		createHelpMenu();
		bar = new JMenuBar();
		addMenusToBar();
	}

	private void addMenusToBar() {
		bar.add(view);
		bar.add(edit);
		bar.add(help);
	}

	private void createHelpMenu() {
		help = new JMenu("帮助(H)");
		addMenuItems(help, 2);
	}

	private void createEditMenu() {
		edit = new JMenu("编辑(E)");
		addMenuItems(edit, 1);
	}

	private void createViewMenu() {
		view = new JMenu("查看(V)");
		view.setMnemonic('V');
		addMenuItems(view, 0);
	}

	private void addMenuItems(JMenu menu, int itemsRow) {
		for (int i = 0; i < items[itemsRow].length; i++) {
			if (items[itemsRow][i].getText().equals(".")) {
				menu.addSeparator();
			} else if (i == items[itemsRow].length - 1
					&& (itemsRow == 0 || itemsRow == 1)) {
				addMenuItems((JMenu) items[itemsRow][i], itemsRow + 3);
				menu.add(items[itemsRow][i]);
			} else {
				items[itemsRow][i].addActionListener(this);
				menu.add(items[itemsRow][i]);
			}
		}
	}

	private void launchFrame() {
		// TODO display the window
		pack();
		setLocation(200,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setJMenuBar(bar);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CalWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		String tip = item.getText();
		switch (tip) {
		case "标准型(T)":
		case "科学型(S)":
		case "程序员型(P)":
		case "统计信息(A)":
			setPanelType(item);
			break;
		case "基本(B)":
		case "单位转换(U)":
		case "日期计算(D)":
		case "抵押(M)":
		case "汽车租赁(V)":
		case "油耗(mpg)(F)":
		case "油耗(|/100km)(U)":
			setExtendPaneType(item);
			break;
		}
	}

	private void setPanelType(JMenuItem item) {
		// TODO change the type of panel
		for (int i = 0; i < 4; i++) {
			items[0][i].setIcon(null);
		}
		item.setIcon(markIcon);
	}

	private void setExtendPaneType(JMenuItem item) {
		// TODO change the extend panel
		for(int i=8; i<items[0].length; i++)
			items[0][i].setIcon(null);
		for(int i=0; i<items[3].length; i++)
			items[3][i].setIcon(null);
		item.setIcon(markIcon);
	}

}
