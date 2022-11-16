package org.vzma.gjc;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GJC {
	private static JTextArea log = new JTextArea();

	public static void main(String args[]){
		final JFrame frame = new JFrame("GeoJSONCoalescer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);

		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		frame.add(new JLabel("Type the desired Master GeoJSON File Name"), c);

		final JTextField masterFileName = new JTextField(20);
		c.gridx = 0;
		c.gridy = 1;
		frame.add(masterFileName, c);

		c.gridx = 0;
		c.gridy = 3;
		frame.add(new JLabel("Click 'ADD FILES' to select the GeoJSON files you would like to coalesce."), c);

		JButton addFileButton = new JButton("ADD FILES");
		c.gridx = 0;
		c.gridy = 4;
		frame.add(addFileButton, c);

		c.gridx = 0;
		c.gridy = 5;
		frame.add(log, c);

		frame.setVisible(true);


		addFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();

				fc.setMultiSelectionEnabled(true);
				fc.showOpenDialog(frame);
				File[] files = fc.getSelectedFiles();

				if (processFiles(masterFileName.getText(), files) == 0){
					JOptionPane.showMessageDialog(null, "Success!");
				} else {
					JOptionPane.showMessageDialog(null, "Failure.  Please try again.");
				}
			}
		});
	}

	public static int processFiles(String masterFileName, File[] files) {
		String finalFeatureString = "";

		List<JSONObject> airportElements = new ArrayList<JSONObject>();

		for (File file : files) {
			log.append(file.getName());

			String rawFileContent = "";
			log.append("1");
			try {
				log.append("2");
				rawFileContent = Files.asCharSource(file, Charsets.UTF_8).read();
				log.append("3");
				log.append(rawFileContent);
			} catch (IOException e) {
				e.printStackTrace();
				log.append("Error opening files");
				return 1;
			}

			JSONObject jsonObject = new JSONObject(rawFileContent);

			JSONArray feature = jsonObject.getJSONArray("features");
			for (int i = 0; i < feature.length(); i++ ){
				airportElements.add(feature.getJSONObject(i));
			}

		}

		JSONObject masterFileJSON = new JSONObject();
		masterFileJSON.put("type", "FeatureCollection");
		masterFileJSON.put("name", masterFileName);
		masterFileJSON.put("features", airportElements);

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(masterFileName + ".geojson", "UTF-8");
			writer.println(masterFileJSON.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.append("File Not Found");
			return 1;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.append("Unsupported Encoding");
			return 1;
		}

		return 0;
	}
}