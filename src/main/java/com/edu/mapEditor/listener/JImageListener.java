package com.edu.mapEditor.listener;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.mapEditor.MapEditorData;
import com.edu.mapEditor.model.State;
import com.edu.mapEditor.view.JImageComponent;

/**
 * 用来实现图片的拖拽
 * @author zuohuai
 */
@Component
public class JImageListener extends MouseInputAdapter {

	/** 坐标点 */
	private Point point = new Point(0, 0);
	/** 监听的组件 */
	@Autowired
	private JImageComponent component;
	@Autowired
	private MapEditorData mapEditorData;
	
	enum ImageDrawType{
		/**编辑网格*/
		EDIT,
		/**设置起始点*/
		START,
		/**设置终点*/
		END
	}

	/**
	 * 当鼠标左键拖动时触发该事件。 记录下鼠标按下(开始拖动)的位置。
	 */
	public void mouseDragged(MouseEvent e) {
		int modify = e.getModifiers();
		
		if (modify == InputEvent.BUTTON3_MASK) {
			// 转换坐标系统
			Point newPoint = SwingUtilities.convertPoint(component, e.getPoint(), component.getParent());
			// 设置标签的新位置
			component.setLocation(component.getX() + (newPoint.x - point.x), component.getY() + (newPoint.y - point.y));
			// 更改坐标点
			point = newPoint;
			// 修改数据存储中的坐标位置
			mapEditorData.modiyfImgPosition(component.getX(), component.getY());
		} else if (modify == InputEvent.BUTTON1_MASK) {
			// 如果不需要绘图，则不处理
			if (mapEditorData.isEditorGird()) {
				drawPoint(e, null, ImageDrawType.EDIT);
			}
			
		}

	}

	/**
	 * 当鼠标左键按下时触发该事件。 记录下鼠标按下(开始拖动)的位置。
	 */
	public void mousePressed(MouseEvent e) {
		int modify = e.getModifiers();
		if (modify == InputEvent.BUTTON3_MASK) {
			// 得到当前坐标点
			point = SwingUtilities.convertPoint(component, e.getPoint(), component.getParent());
		} else if (modify == InputEvent.BUTTON1_MASK) {
			// 如果不需要绘图，则不处理
			if (mapEditorData.isEditorGird()) {
				drawPoint(e, null, ImageDrawType.EDIT);			}
			if(mapEditorData.isCanStart()){
				if(mapEditorData.getStart() == null){
					drawPoint(e,Color.YELLOW, ImageDrawType.START);
					//设置起始Node
					int x = e.getY()/mapEditorData.getPrixel();
					int y = e.getX()/mapEditorData.getPrixel();
					mapEditorData.changeStart(x, y);
				}
			}
			if(mapEditorData.isCanEnd()){
				if(mapEditorData.getEnd() == null){
					drawPoint(e,Color.RED, ImageDrawType.END);
					//设置终点Node
					int x = e.getY()/mapEditorData.getPrixel();
					int y = e.getX()/mapEditorData.getPrixel();
					mapEditorData.changeEnd(x, y);
				}
			}
			
		}

	}

	private void drawPoint(MouseEvent e, Color color, ImageDrawType type) {
		int currentX = e.getX();
		int currentY = e.getY();

		int numX = currentX / mapEditorData.getPrixel();
		int numY = currentY / mapEditorData.getPrixel();

		System.out.println("current:" + currentX + "," + currentY);
		System.out.println("num:" + numX + "," + numY);

		int afterX = mapEditorData.getPrixel() * numX;
		int afterY = mapEditorData.getPrixel() * numY;

		Graphics g = this.component.getGraphics();
		Color c = g.getColor();

		Graphics2D g2d = (Graphics2D) g;
		if(color != null){
			g.setColor(color);
		}
		// 设置透明度
		Composite current = g2d.getComposite();
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
		g2d.setComposite(ac);

		g2d.fill3DRect(afterX, afterY, mapEditorData.getPrixel(), mapEditorData.getPrixel(), false);
		g2d.draw3DRect(afterX, afterY, mapEditorData.getPrixel(), mapEditorData.getPrixel(), false);
		// 将颜色还原
		g.setColor(c);
		g2d.setComposite(current);
		if(type == ImageDrawType.EDIT){
			mapEditorData.modifyPoint(com.edu.mapEditor.model.Point.valueOf(afterX, afterY), State.BLOCK);
		}
		this.component.repaint();
	}

	public JImageComponent getComponent() {
		return component;
	}

}
