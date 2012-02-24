package soopia.hwp.hexdump;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.util.IOUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import soopia.hwp.hexdump.view.HexviewPanel;

public class HwpHexViewer extends JFrame {
	
	private HexviewPanel hexviewPanel;
	public HwpHexViewer(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFileOpen();
			}
		});
		mnFile.add(mntmOpen);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(4);
		splitPane.setContinuousLayout(true);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel sizeInfoPanel = new JPanel();
		panel.add(sizeInfoPanel, BorderLayout.NORTH);
		GridBagLayout gbl_sizeInfoPanel = new GridBagLayout();
		gbl_sizeInfoPanel.columnWidths = new int[]{0, 0, 0};
		gbl_sizeInfoPanel.rowHeights = new int[]{0, 0};
		gbl_sizeInfoPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_sizeInfoPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		sizeInfoPanel.setLayout(gbl_sizeInfoPanel);
		
		JLabel lblNewLabel = new JLabel("SIZE");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		sizeInfoPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblSize = new JLabel("304 bytes");
		GridBagConstraints gbc_lblSize = new GridBagConstraints();
		gbc_lblSize.gridx = 1;
		gbc_lblSize.gridy = 0;
		sizeInfoPanel.add(lblSize, gbc_lblSize);
		
		hexviewPanel = new HexviewPanel(16, 14);
		panel.add(hexviewPanel);
		
	}
	protected void showFileOpen() {
		JFileChooser fChooser = new JFileChooser();
		
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
		
		if ( retOption == JFileChooser.APPROVE_OPTION ){
			File f = fChooser.getSelectedFile();
			try {
				byte [] data = this.readFile(f);
				hexviewPanel.print(data, 0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public byte [] readFile (File file) throws IOException{
		final ByteArrayOutputStream bas = new ByteArrayOutputStream(8*1024);
		POIFSReader poiReader = new POIFSReader();
		poiReader.registerListener(new POIFSReaderListener() {
			@Override
			public void processPOIFSReaderEvent(POIFSReaderEvent event) {
				DocumentInputStream dis = event.getStream();
				try {
					if ( "DocInfo".equals(event.getName()) ) {
						bas.write(IOUtils.toByteArray(dis));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		poiReader.read(new FileInputStream(file));
		return bas.toByteArray();
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				HwpHexViewer hv = new HwpHexViewer();
				hv.pack();
				hv.setVisible(true);
				
			}
		});
	}
}
