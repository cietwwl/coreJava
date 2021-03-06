package com.edu.mapEditor.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.mapEditor.MapEditorData;

/**
 * 编辑路径的监听器
 * @author zuohuai
 *
 */
@Component
public class JBtnEditorGirdListener implements ActionListener{
	@Autowired
	private MapEditorData mapEditorData;
	@Override
	public void actionPerformed(ActionEvent e) {
		if(mapEditorData.isEditorGird()){
			mapEditorData.modifyEditorGird(false);	
		}else{
			mapEditorData.modifyEditorGird(true);	
		}	
	}
}
