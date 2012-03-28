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
import java.awt.Toolkit;

import javax.swing.JSeparator;
import javax.swing.filechooser.FileFilter;
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
import soopia.hwp.hexdump.view.DSTreeNode;
import soopia.hwp.hexdump.view.HexviewPanel;
import soopia.hwp.type.HwpContext;
import soopia.hwp.type.IDataType;
import soopia.hwp.type.IRecordStructure;
import soopia.hwp.type.IStreamStruct;
import soopia.hwp.type.stream.DocInfoStream;
import soopia.hwp.type.stream.SectionStream;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.awt.Component;
import java.beans.PropertyVetoException;
/**
 * 본 제품은 한글과컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.
 * 
 * @author chmin
 *
 */
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
					DSTreeNode node = (DSTreeNode) path.getLastPathComponent();
					if ( node.getUserObject() instanceof IDataType )
						createInternalFrame(path, (IDataType)node.getUserObject());
				}
			}
		});
		scrollPane.setViewportView(tree);
		
		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);
		splitPane.setRightComponent(desktopPane);
		splitPane.setDividerLocation(300);
		
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
//	private void createTreeNodes (final String hwpFilePath, final List<IDataType> dsList ){
	private void createTreeNodes (final String hwpFilePath, final HwpContext ctx ){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DSTreeNode rootNode, ctxNode, streamNode, recordNode ;
				DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
				rootNode = (DSTreeNode) treeModel.getRoot();
				ctxNode = new DSTreeNode(
						hwpFilePath.substring(hwpFilePath.lastIndexOf(File.separator) + 1),
						true,
						DSTreeNode.TYPE_TOP_LEVEL);
				
				/* Context Node */
				treeModel.insertNodeInto(ctxNode, rootNode, treeModel.getChildCount(rootNode));
				
				/* File Header  */
				streamNode = new DSTreeNode(ctx.getFileHeaderInfo(),false, DSTreeNode.TYPE_STREAM);
				treeModel.insertNodeInto(streamNode, ctxNode, 0);
				
				/* Doc Info */
				streamNode = new DSTreeNode(ctx.getDocInfo(), true, DSTreeNode.TYPE_STREAM); 
				treeModel.insertNodeInto(streamNode, ctxNode, 1);
				List<? extends IRecordStructure> recordStruct = ((DocInfoStream)ctx.getDocInfo()).getRecord(null); 
//						factory.createRecordStructures((IStreamStruct)ctx.getDocInfo()) ;
				for(IRecordStructure rs : recordStruct ){
					recordNode = new DSTreeNode(rs, false, DSTreeNode.TYPE_RECORD);
					treeModel.insertNodeInto(recordNode, streamNode, treeModel.getChildCount(streamNode));
				}
				
				/* Summary Information */
				streamNode = new DSTreeNode(ctx.getSummary(), true, DSTreeNode.TYPE_STREAM); 
				treeModel.insertNodeInto(streamNode, ctxNode, 2);
				
				/* bodyText (SectionX) */
				List<IStreamStruct> sections = ctx.getSections();
//				streamNode = new DSTreeNode(ctx.getSummary(), true, DSTreeNode.TYPE_STREAM); 
				for( IDataType sct : sections ){
					streamNode = new DSTreeNode(sct, true, DSTreeNode.TYPE_RECORD);
					treeModel.insertNodeInto(streamNode, ctxNode, treeModel.getChildCount(ctxNode));
					recordStruct = ((SectionStream)sct).getRecord(null);
					for(IRecordStructure rs : recordStruct ){
						recordNode = new DSTreeNode(rs, false, DSTreeNode.TYPE_RECORD);
						treeModel.insertNodeInto(recordNode, streamNode, treeModel.getChildCount(streamNode));
					}
				}
				/* prvImage, prvText */
				streamNode = new DSTreeNode(ctx.getPreviewImage(), false, DSTreeNode.TYPE_STREAM); 
				treeModel.insertNodeInto(streamNode, ctxNode, treeModel.getChildCount(ctxNode));
				streamNode = new DSTreeNode(ctx.getPreviewText(), false, DSTreeNode.TYPE_STREAM); 
				treeModel.insertNodeInto(streamNode, ctxNode, treeModel.getChildCount(ctxNode));
				/* Binary Data */
				List<IStreamStruct> bins = ctx.getBinaryData();
				for( IDataType dt : bins){
					streamNode = new DSTreeNode(dt, false, DSTreeNode.TYPE_STREAM); 
					treeModel.insertNodeInto(streamNode, ctxNode, treeModel.getChildCount(ctxNode));
				}
				TreePath treePath = new TreePath(treeModel.getPathToRoot(ctxNode));
				HwpHexViewer.this.tree.setSelectionPath(treePath);
				tree.expandPath(treePath);
			}
		});
	}
	
	/*
	 * tree에서 선택한 structure 정보를 internal frame에 출력.
	 */
	private void createInternalFrame (TreePath path, IDataType ds){
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
//		hexviewPanel.print(ds.getBytes(), ds.getOffset());
		hexviewPanel.print(ds.getBytes(), 0);
		
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
		if ( iframeOffset.x > 300){
			iframeOffset.x = 10;
			iframeOffset.y = 20;
		}
		return new Point(iframeOffset);
	}
	class FileModelHandle extends DefaultTreeModel implements FileModelListener {
		private static final long serialVersionUID = 1L;

		public FileModelHandle() {
			super(new DSTreeNode("HWP Files", true, DSTreeNode.TYPE_ROOT));
		}

		@Override
		public void fileAdded(final FileModelEvent event) {
			createTreeNodes(event.getFilePath(), (HwpContext)event.getObject());
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
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				
				hv.setSize(new Dimension(dim.width-300, dim.height-300));
				hv.setLocationRelativeTo(null);
				hv.setVisible(true);
				
			}
		});
	}
}
