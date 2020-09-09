package ovc.utilities;

import common.Constants;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Assert;
import ovc.data.ConstantsOvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by mginevan on 2/21/2018.
 */
public class OvcExcel {

    private final static Logger LOGGER = Logger.getLogger(OvcExcel.class.getName());

    /**
     * Pulls OVC order number from scenario data and saves it to external excel file
     *
     * @param transactionData Passed through as scenario data
     */
    public void OVCOrderNumberToExcel(String filePath, String transactionData) throws Exception {
        LOGGER.info("OVCOrderNumberToExcel started");
        try {
            File excel = new File(filePath);
            FileInputStream file = new FileInputStream(excel);
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            String orderData[] = transactionData.split(" ");

            if (filePath.contains(ConstantsOvc.TLOG)) {
                HSSFSheet sheet = workbook.getSheet(orderData[0]);
                int lastRowIndex = sheet.getLastRowNum();
                Row orderRow = sheet.createRow(lastRowIndex + 1);
                orderRow.createCell(0).setCellValue(orderData[0]);
                orderRow.createCell(1).setCellValue(orderData[1]);
                orderRow.createCell(2).setCellValue(orderData[2]);
            } else if (filePath.contains(ConstantsOvc.E2E)) {
                workbook.cloneSheet(0);
                workbook.setSheetName(1, orderData[2]);
                workbook.removeSheetAt(0);
                HSSFSheet sheet = workbook.getSheet(orderData[2]);
                Row orderRow = sheet.createRow(2);
                orderRow.createCell(0).setCellValue(orderData[0]);
                orderRow.createCell(1).setCellValue(orderData[1]);
                orderRow.createCell(2).setCellValue(orderData[2]);
            } else {
                Assert.fail("FAIL: File Path '" + filePath + "' is not a valid endpoint for this method!");
            }

            if (excel.canWrite()) {
                FileOutputStream fileOut = new FileOutputStream(filePath);
                FileChannel channel = fileOut.getChannel();
                FileLock lock = channel.lock();
                workbook.write(fileOut);
                lock.release();
                fileOut.close();
                workbook.close();
                file.close();
            } else {
                FileLock lock = file.getChannel().tryLock();
                lock.release();
            }

        } catch (IOException err) {
            Thread.sleep(Constants.FIVE_SEC_WAIT); //wait for other process to unlock file

            try {
                FileLock lock = new RandomAccessFile(new File(filePath), "rw").getChannel().tryLock();
                lock.release();
                OVCOrderNumberToExcel(filePath, transactionData);
            } catch (Exception e) {
                Assert.fail("FAIL: File located at '" + filePath +
                        "' is currently locked by another process and cannot be accessed!");
            }
        }
        LOGGER.info("OVCOrderNumberToExcel completed");
    }

    /**
     * formats the transaction data that is sent to external excel
     *
     * @return transaction data string with environment, timestamp, and order number
     */
    public String formatExcelWriteData(String orderNumber) {
        LOGGER.info("formatExcelWriteData started");
        String environment = "";
        if (System.getenv("OVC_SERVER").contains(ConstantsOvc.TEST)) {
            environment = ConstantsOvc.TEST;
        } else if (System.getenv("OVC_SERVER").contains(ConstantsOvc.STAGE)) {
            environment = ConstantsOvc.STAGE;
        }
        String currentTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String formattedWriteData = environment + " " + currentTime + " " + orderNumber;
        LOGGER.info("formatExcelWriteData completed");
        return formattedWriteData;
    }
}
