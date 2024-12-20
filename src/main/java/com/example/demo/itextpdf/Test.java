package com.example.demo.itextpdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

public class Test {

    public static final String FONT_PATH="D:\\work\\需求0424\\simsun.TTF";

    public static void main(String[] args) throws Exception {

        Rectangle rectPageSize = new Rectangle(PageSize.A4);// 定义页面大小
        Document document = new Document(rectPageSize, 0, 0, 5, 0); //边距
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//构建字节输出流
        PdfWriter writer = PdfWriter.getInstance(document, baos);//将PDF文档对象写入到流
        BaseFont bf = BaseFont
            .createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(bf, 9, Font.NORMAL);
        float columnNums[] = new float[]{1.0f,1.0f};
        PdfPTable table = new PdfPTable(columnNums);
        table.addCell(getPDFCell("ceshi1", font, 2, 1));
        table.addCell(getPDFCell("ceshi2", font));
        table.addCell(getPDFCell("ceshi2", font));



        document.open();
        document.add(table);
        document.close();
        FileOutputStream outputStream = new FileOutputStream("D:\\work\\需求0424\\dbt.pdf");
        outputStream.write(baos.toByteArray());
        baos.flush();
        outputStream.flush();
        baos.close();
        outputStream.close();
        writer.close();

    }

    public static PdfPCell getPDFCell(String string, Font font) {
        return getPDFCell(string, font, 1, 1);
    }

    private static PdfPCell getPDFCell(String string, Font font, Integer rowSpan, Integer colSpan) {
        if (StringUtils.isNotEmpty(string) && string.contains("mfs.banksteel.com")) {
            return getPdfImageCell(string, font);
        }
        //创建单元格对象，将内容与字体放入段落中作为单元格内容
        PdfPCell cell = new PdfPCell(new Paragraph(string, font));
        if (true) {
            cell.setBackgroundColor(new BaseColor(233, 243, 252));
        }

        cell.setMinimumHeight(25);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(rowSpan);
        cell.setColspan(colSpan);
        return cell;
    }

    private static PdfPCell getPdfImageCell(String string, Font font) {
        Image image = null;
        try {
            String head = string.substring(0, string.lastIndexOf("/") + 1);
            String suffix = string.substring(string.lastIndexOf("/") + 1);
            String link = head + URLEncoder.encode(suffix, StandardCharsets.UTF_8.toString());
            image = Image.getInstance(link);
        } catch (Exception e) {
            return getPDFCell("获取签名失败", font);
        }
        image.setAlignment(Image.ALIGN_CENTER);
        image.scalePercent(40); //依照比例缩放
        image.setAbsolutePosition(40, 60);
        //创建单元格对象，将内容与字体放入段落中作为单元格内容
        PdfPCell cell = new PdfPCell();
        cell.setMinimumHeight(25);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setImage(image);
        return cell;
    }

/*private static PdfPCell mergeImgCell(HSSFCell hcell, String str, Font font, String cellInfo, Set<String> cellFontColor, int rowNum, int colNum) {
        PdfPCell cell = new PdfPCell();
        cell.setMinimumHeight(25);
        //设置合并单元格内容水平对齐方式
        HSSFCellStyle style = hcell.getCellStyle();
        if (style.getAlignmentEnum() == HorizontalAlignment.CENTER) {
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        } else if (style.getAlignmentEnum() == HorizontalAlignment.LEFT) {
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        } else if (style.getAlignmentEnum() == HorizontalAlignment.RIGHT) {
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        }
        //设置合并单元格内容垂直对齐方式
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(rowNum);
        cell.setColspan(colNum);
        Image image = null;
        try {
            String head = str.substring(0, str.lastIndexOf("/") + 1);
            String suffix = str.substring(str.lastIndexOf("/") + 1);
            String link = head + URLEncoder.encode(suffix, StandardCharsets.UTF_8.toString());
            image = Image.getInstance(link);
        } catch (Exception e) {
            return mergeTextCell(hcell, "获取签名失败", font, cellInfo, cellFontColor, rowNum, colNum);
        }
        image.setAlignment(Image.ALIGN_CENTER);
        image.scalePercent(40); //依照比例缩放
        image.setAbsolutePosition(40, 60);
        cell.setImage(image);
        return cell;
    }*/

    @SneakyThrows
    private static PdfPCell mergeTextCell( String str, Font font, int rowNum, int colNum) {
        Paragraph paragraph = new Paragraph(str, font);
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setMinimumHeight(25);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //设置合并单元格内容垂直对齐方式
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(rowNum);
        cell.setColspan(colNum);
        return cell;
    }

}
