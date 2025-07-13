package utils;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;

public class WordDocumentUtil {
    private XWPFDocument document;
    private String filePath;

    public WordDocumentUtil(String filePath) {
        this.document = new XWPFDocument();
        this.filePath = filePath;
    }

    public void addStepWithScreenshot(String stepText, byte[] screenshot) {
        try {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(stepText);
            run.addBreak();

            try (InputStream picStream = new ByteArrayInputStream(screenshot)) {
                run.addPicture(picStream, XWPFDocument.PICTURE_TYPE_PNG, "screenshot.png", Units.toEMU(500), Units.toEMU(300));
            }
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAndClose() {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
