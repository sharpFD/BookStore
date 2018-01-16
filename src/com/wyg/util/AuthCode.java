package com.wyg.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * ������֤��ͼƬ
 * @author �ֱ���Evankaka�����ͣ�http://blog.csdn.net/evankaka��
 * @since 2015.6.22
 */
public class AuthCode {
	public static final int AUTHCODE_LENGTH = 4; // ��֤�볤��
	public static final int SINGLECODE_WIDTH = 15; // ������֤����
	public static final int SINGLECODE_HEIGHT = 30; // ������֤��߶�
	public static final int SINGLECODE_GAP = 4; // ������֤��֮����
	public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP);
	public static final int IMG_HEIGHT = SINGLECODE_HEIGHT;
	public static final char[] CHARS = {'0','1', '2', '3', '4', '5', '6', '7', '8',
		'9','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	static Random random = new Random();
	
    /**
     * ����ͼƬ�е�����
     * @return String
     */
	public static String getAuthCode() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 5; i++) {// ����6���ַ�
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}
	
	 /**
     * ���ش����ֵ�ͼƬ
     * @return BufferedImage
     */
	public static BufferedImage getAuthImg(String authCode) {
		// ����ͼƬ�ĸߡ�������
		// RGB���룺red��green��blue
		BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
				BufferedImage.TYPE_INT_BGR);
		// �õ�ͼƬ�ϵ�һ������
		Graphics g = img.getGraphics();
		// ���û��ʵ���ɫ������������ɫ
		g.setColor(Color.WHITE);
		// �û��������һ�����Σ����ε����Ͻ����꣬����
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
		// ��������ɫ����Ϊ��ɫ������д��
		g.setColor(Color.BLACK);
		// �������壺���塢������ʽ�ġ��ֺ�
		g.setFont(new Font("����", Font.PLAIN, SINGLECODE_HEIGHT + 5));
		// �������
		char c;
		for (int i = 0; i < authCode.toCharArray().length; i++) {
			// ȡ����Ӧλ�õ��ַ�
			c = authCode.charAt(i);
			// ����һ���ַ�����Ҫ�������ݣ���ʼ��λ�ã��߶�
			g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP)
					+ SINGLECODE_GAP / 2, IMG_HEIGHT);
		}
		Random random = new Random();
		// ������
		for (int i = 0; i < 15; i++) {
			int x = random.nextInt(IMG_WIDTH);
			int y = random.nextInt(IMG_HEIGHT);
			int x2 = random.nextInt(IMG_WIDTH);
			int y2 = random.nextInt(IMG_HEIGHT);
			g.drawLine(x, y, x + x2, y + y2);
		}
		return img;
	}
}