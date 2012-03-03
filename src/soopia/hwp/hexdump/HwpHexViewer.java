package soopia.hwp.hexdump;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JSeparator;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileFilter;
//import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import soopia.hwp.hexdump.model.FileModel;
import soopia.hwp.hexdump.model.FileModelEvent;
import soopia.hwp.hexdump.model.FileModelListener;
import soopia.hwp.hexdump.view.DataStructureTreeNode;
import soopia.hwp.hexdump.view.HexviewPanel;
import soopia.hwp.structure.IDataStructure;
import soopia.hwp.structure.IRecordStructure;
import soopia.hwp.structure.RecordStructureFactory;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.awt.Component;
import java.beans.PropertyVetoException;

public class HwpHexViewer extends JFrame {
	
	JDesktopPane desktopPane ; 
	FileModel fileModel;
	private FileModelHandle fileHandler;
	
	public HwpHexViewer(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final File hwpFile = showFileOpenDialog();
				if ( hwpFile == null) return ;
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run() {
						openStorage(hwpFile);
					}
				});
			}
		});
		mnFile.add(mntmOpen);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		tree = new JTree();
		tree.setCellRenderer(new HexviewTreeRenderer());
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( e.getClickCount() >1 && SwingUtilities.isLeftMouseButton(e)){
					JTree tree = (JTree) e.getSource();
					TreePath path = tree.getSelectionPath();
					DataStructureTreeNode node = 
							(DataStructureTreeNode) path.getLastPathComponent();
					if ( node.getUserObject() instanceof IDataStructure )
						createInternalFrame(path, (IDataStructure)node.getUserObject());
				}
			}
		});
		scrollPane.setViewportView(tree);
		
		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);
		splitPane.setRightComponent(desktopPane);
		
		installController();
		
	}
	
	private void installController(){
		this.fileModel = new FileModel();
		this.fileHandler = new FileModelHandle();
		this.fileModel.addFileModelListener(this.fileHandler);
		this.tree.setModel(this.fileHandler);
	}
	
	/*
	 * 새로운 파일을 열었을때 tree 구조를 생성한다.
	 */
	private void createTreeNodes (final String hwpFilePath, final List<IDataStructure> dsList ){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DataStructureTreeNode root, fNameNode, streamNode, recordNode ;
				DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
				root = (DataStructureTreeNode) treeModel.getRoot();
				fNameNode = new DataStructureTreeNode(
						hwpFilePath.substring(hwpFilePath.lastIndexOf(File.separator) + 1),
						true,
						DataStructureTreeNode.TYPE_TOP_LEVEL);
				// 1. add File Name node
				treeModel.insertNodeInto(fNameNode, root, treeModel.getChildCount(root));
				
				TreePath treePath = new TreePath(treeModel.getPathToRoot(fNameNode));
				RecordStructureFactory factory = new RecordStructureFactory();
				List<IRecordStructure> recordDS;
				for (IDataStructure ds : dsList) {
					streamNode = new DataStructureTreeNode(
							ds,
							true,
							DataStructureTreeNode.TYPE_STREAM); // docInfo, FileHader etc
					// 2. add StreamStructure Node
					treeModel.insertNodeInto(streamNode, fNameNode, 0);
					
					if ( "DocInfo".equals(ds.getStrucureName()) || ds.getStrucureName().startsWith("Section") ){						
						recordDS = factory.createRecordStructures(ds);
						for( IRecordStructure rs : recordDS){
							recordNode = new DataStructureTreeNode( rs,false,
									DataStructureTreeNode.TYPE_RECORD);
							// 3. add RecordStructure Node
							treeModel.insertNodeInto(
									recordNode,
									streamNode,
									treeModel.getChildCount(streamNode));
						}
					}
				}
				HwpHexViewer.this.tree.setSelectionPath(treePath);
				tree.expandPath(treePath);
			}
		});
	}
	
	/*
	 * tree에서 선택한 structure 정보를 internal frame에 출력.
	 */
	private void createInternalFrame (TreePath path, IDataStructure ds){
		// 이미 열려있는지 확인
		String key = path.toString();
		Component [] coms = desktopPane.getComponents();
		for(Component c : coms){
			if ( c.getName().equals(key)){
				try {
					((JInternalFrame)c).setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				return ;
			}
		}
		// 없으므로 새창을 띄운다.
		JInternalFrame internalFrame = new JInternalFrame(ds.getStrucureName()); 
		internalFrame.setIconifiable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setResizable(true);
		internalFrame.setDoubleBuffered(true);
		internalFrame.setClosable(true);
		internalFrame.setName(key);
		desktopPane.add(internalFrame);
		
		HexviewPanel hexviewPanel = new HexviewPanel();
		internalFrame.getContentPane().add(hexviewPanel, BorderLayout.CENTER);
		internalFrame.setVisible(true);
		hexviewPanel.print(ds.getBytes(), ds.getOffset());
		
		Dimension dim = internalFrame.getPreferredSize();
		Rectangle rtgl = new Rectangle(dim.width+25, Math.min(dim.height, 400) );
		rtgl.setLocation(getNextIframePos());
		internalFrame.pack();
		internalFrame.setBounds(rtgl);
	}
	
	private Point iframeOffset = new Point(10, 10);
	private Point getNextIframePos() {
		iframeOffset.x += 15;
		iframeOffset.y += 15;
		return new Point(iframeOffset);
	}
	class FileModelHandle extends DefaultTreeModel implements FileModelListener {
		private static final long serialVersionUID = 1L;

		public FileModelHandle() {
			super(new DataStructureTreeNode("HWP Files", true, DataStructureTreeNode.TYPE_ROOT));
		}

		@Override
		public void fileAdded(final FileModelEvent event) {
			createTreeNodes(event.getFilePath(), event.getDataStructureList());
		}
	}
	/**
	 * 주어진 파일에 대해서 tree 구조 생성.
	 * @param hwpFile
	 */
	private void openStorage (File hwpFile ){
		try {
			fileModel.addFile(hwpFile);
		} catch (IOException e) {
			// TODO 읽기 실패 메세지 출력.
			e.printStackTrace();
		}
	}
	
	JFileChooser fChooser = new JFileChooser();
	private JTree tree;
	private File showFileOpenDialog(){
		
		fChooser.setFileFilter (new FileFilter() {
			
			@Override
			public String getDescription() {
				return "*.HWP";
			}
			
			@Override
			public boolean accept(File f) {
				return f.isDirectory()
						|| f.isFile() && f.getName().toUpperCase().endsWith("HWP");
			}
		});
		
		int retOption = fChooser.showOpenDialog(this);
		
		return ( retOption == JFileChooser.APPROVE_OPTION ) ? fChooser.getSelectedFile() : null ;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
//				      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//				      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				    } catch (Exception e) {
				      e.printStackTrace();
				    }
				
				HwpHexViewer hv = new HwpHexViewer();
				hv.setSize(new Dimension(800, 600));
				
				hv.setVisible(true);
				
			}
		});
	}
}
