package common.component.applet.util;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {
    /**
     ��* ���캯��
     ��*
     ��* @param icon ͼ��ͼ��
     ��*/
    public ImageButton(ImageIcon icon, ImageIcon pressedIcon,ImageIcon disabledIcon) {
        //���ÿհ׼��
        setMargin(new Insets(0, 0, 0, 0));
        //�����ı���ͼ��֮��ļ��
        setIconTextGap(0);
        //ָ���Ƿ���Ʊ߿�
        setBorderPainted(false);
        //���ñ߿�
        setBorder(null);
        //�����ı�
        setText(null);
        if (icon != null) {
            //����ͼ��
            setIcon(icon);
            setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
        }
        if (pressedIcon != null) {
            setPressedIcon(pressedIcon);
            
            setSize(pressedIcon.getImage().getWidth(null),
                    pressedIcon.getImage().getHeight(null));
        }
        
        if (disabledIcon != null) {
        	setDisabledIcon(disabledIcon);
            
            setSize(disabledIcon.getImage().getWidth(null),
            		disabledIcon.getImage().getHeight(null));
        }
        this.setBackground(new Color(249, 249, 249));

    }

    public ImageButton(ImageIcon icon) {
        this(icon, null,null);

    }
    
    public ImageButton(ImageIcon icon, ImageIcon pressedIcon) {
        this(icon, pressedIcon,null);

    }

}
