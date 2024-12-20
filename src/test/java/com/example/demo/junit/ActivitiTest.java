//package com.example.demo.junit;
//
//import cn.hutool.core.util.RandomUtil;
//import com.caucho.hessian.io.Hessian2Input;
//import com.caucho.hessian.io.Hessian2Output;
//import com.example.demo.User;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.net.URL;
//import java.time.Duration;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//import org.activiti.bpmn.BpmnAutoLayout;
//import org.activiti.bpmn.converter.BpmnXMLConverter;
//import org.activiti.bpmn.model.ActivitiListener;
//import org.activiti.bpmn.model.BpmnModel;
//import org.activiti.bpmn.model.CustomProperty;
//import org.activiti.bpmn.model.EndEvent;
//import org.activiti.bpmn.model.ExtensionAttribute;
//import org.activiti.bpmn.model.FormProperty;
//import org.activiti.bpmn.model.ImplementationType;
//import org.activiti.bpmn.model.InclusiveGateway;
//import org.activiti.bpmn.model.ParallelGateway;
//import org.activiti.bpmn.model.Process;
//import org.activiti.bpmn.model.SequenceFlow;
//import org.activiti.bpmn.model.StartEvent;
//import org.activiti.bpmn.model.UserTask;
//import org.activiti.engine.ProcessEngineConfiguration;
//import org.activiti.engine.impl.bpmn.behavior.ParallelGatewayActivityBehavior;
//import org.activiti.engine.impl.bpmn.parser.BpmnParse;
//import org.activiti.engine.impl.bpmn.parser.BpmnParseHandlers;
//import org.activiti.engine.impl.bpmn.parser.factory.DefaultActivityBehaviorFactory;
//import org.activiti.engine.impl.bpmn.parser.handler.BoundaryEventParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.BusinessRuleParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.CallActivityParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.CancelEventDefinitionParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.CompensateEventDefinitionParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.EndEventParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.ErrorEventDefinitionParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.EventBasedGatewayParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.EventSubProcessParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.ExclusiveGatewayParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.InclusiveGatewayParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.IntermediateCatchEventParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.IntermediateThrowEventParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.ManualTaskParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.MessageEventDefinitionParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.ParallelGatewayParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.ProcessParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.ReceiveTaskParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.ScriptTaskParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.SendTaskParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.SequenceFlowParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.ServiceTaskParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.SignalEventDefinitionParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.StartEventParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.SubProcessParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.TaskParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.TimerEventDefinitionParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.TransactionParseHandler;
//import org.activiti.engine.impl.bpmn.parser.handler.UserTaskParseHandler;
//import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
//import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
//import org.activiti.engine.impl.history.parse.FlowNodeHistoryParseHandler;
//import org.activiti.engine.impl.history.parse.ProcessHistoryParseHandler;
//import org.activiti.engine.impl.history.parse.StartEventHistoryParseHandler;
//import org.activiti.engine.impl.history.parse.UserTaskHistoryParseHandler;
//import org.activiti.engine.impl.interceptor.CommandInterceptor;
//import org.activiti.engine.impl.persistence.StrongUuidGenerator;
//import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
//import org.activiti.engine.impl.util.IoUtil;
//import org.activiti.engine.impl.util.io.BytesStreamSource;
//import org.activiti.engine.impl.util.io.ResourceStreamSource;
//import org.activiti.engine.parse.BpmnParseHandler;
//import org.activiti.image.impl.DefaultProcessDiagramGenerator;
//import org.activiti.spring.SpringProcessEngineConfiguration;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.junit.jupiter.api.Test;
//
///**
// * @author wuzhenhong
// * @date 2023/8/1 16:01
// */
//public class ActivitiTest {
//
//    @Test
//    public void ddddddddddd() {
//        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
//        processEngineConfiguration.setDataSource(null);
//        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE);
//        processEngineConfiguration.setTransactionManager(null);
//        processEngineConfiguration.setProcessDefinitionCacheLimit(10);
//
//        Process process = new Process();
//        process.setId("myProcess");
//
//        StartEvent startEvent = new StartEvent();
//        startEvent.setId("start");
//        process.addFlowElement(startEvent);
//
//        EndEvent endEvent = new EndEvent();
//        endEvent.setId("end");
//        process.addFlowElement(endEvent);
//
//        BpmnParse bpmnParse = processEngineConfiguration.getBpmnParser().createParse();
//        bpmnParse.getBpmnParserHandlers().parseElement(bpmnParse, process);
//        ProcessDefinitionEntity processDefinition = bpmnParse.getProcessDefinition("myProcess");
//
//        System.out.println(processDefinition);
//
//    }
//
//
//    @org.junit.jupiter.api.Test
//    public void 神鼎飞丹砂发() {
//        try (PDDocument document = PDDocument.load(new File("D:\\work\\建投四期\\系统建设方案.pdf"))) {
//            PDFTextStripper pdfStripper = new PDFTextStripper();
//            String text = pdfStripper.getText(document);
//
//            try (XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream("D:\\work\\建投四期\\output.docx")) {
//                XWPFParagraph paragraph = doc.createParagraph();
//                paragraph.createRun().setText(text);
//                doc.write(out);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    public void dddd() {
//        System.out.println(Arrays.asList("a", "b").stream().collect(Collectors.joining("\\")));
//    }
//
//    @org.junit.jupiter.api.Test
//    public void t_5() throws Exception {
//
//        File file = new File("D:\\work\\建投四期\\666\\xx.zip");
//        FileOutputStream outputStream = new FileOutputStream(file);
//        ZipOutputStream zipOut = new ZipOutputStream(outputStream);
//
//        tddd(zipOut, "D:\\work\\建投四期\\666\\nohup(2).out", "a/b/c/");
//        tddd(zipOut, "D:\\work\\建投四期\\666\\补充需求V6-4配置和脚本20240717 -建表.txt", "c/");
//
//        zipOut.flush();
//        zipOut.close();
//        outputStream.close();
//    }
//
//    private void tddd(ZipOutputStream zipOut, String src, String dir) throws IOException {
//        File f = new File(src);
//        FileInputStream inputStream = new FileInputStream(f);
//        byte[] bytes = new byte[1024];
//        int len = -1;
//        zipOut.putNextEntry(new ZipEntry(dir + f.getName()));
//        while((len = inputStream.read(bytes)) > 0) {
//            zipOut.write(bytes, 0, len);
//        }
//        zipOut.closeEntry();
//        inputStream.close();
//    }
//
//    @org.junit.jupiter.api.Test
//    public void t_4() throws Exception {
//        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
//        BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(new ResourceStreamSource("bpmn/xxx.bpmn"), false, false);
//        new BpmnAutoLayout(bpmnModel).execute();
//        File file = new File("D:\\java_project\\demo\\src\\test\\resources\\bpmn\\xxx.bpmn");
//        FileOutputStream outputStream = new FileOutputStream(file);
//        byte[] bytes = new BpmnXMLConverter().convertToXML(bpmnModel, "UTF-8");
//        outputStream.write(bytes);
//        outputStream.close();
//
//        DefaultProcessDiagramGenerator defaultProcessDiagramGenerator = new DefaultProcessDiagramGenerator();
//        InputStream inputStream = defaultProcessDiagramGenerator.generateDiagram(bpmnModel, "png", Collections.emptyList());
//        file = new File("D:\\java_project\\demo\\src\\test\\resources\\bpmn\\xxx.png");
//        outputStream = new FileOutputStream(file);
//        outputStream.write(IoUtil.readInputStream(inputStream, ""));
//        outputStream.close();
//
//        List<BpmnParseHandler> parseHandlers = getDefaultBpmnParseHandlers();
//        BpmnParseHandlers bpmnParseHandlers = new BpmnParseHandlers();
//        bpmnParseHandlers.addHandlers(parseHandlers);
//        for(Process process : bpmnModel.getProcesses()) {
////            bpmnParseHandlers.parseElement();
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    public void t_3() throws Exception {
//        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
//        BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(new ResourceStreamSource("bpmn/嵌入式子流程.bpmn"), false, false);
//        new BpmnAutoLayout(bpmnModel).execute();
//        File file = new File("D:\\java_project\\demo\\src\\test\\resources\\bpmn\\嵌入式子流程.bpmn");
//        FileOutputStream outputStream = new FileOutputStream(file);
//        byte[] bytes = new BpmnXMLConverter().convertToXML(bpmnModel, "UTF-8");
//        outputStream.write(bytes);
//        outputStream.close();
//
//        DefaultProcessDiagramGenerator defaultProcessDiagramGenerator = new DefaultProcessDiagramGenerator();
//        InputStream inputStream = defaultProcessDiagramGenerator.generateDiagram(bpmnModel, "png", Collections.emptyList());
//         file = new File("D:\\java_project\\demo\\src\\test\\resources\\bpmn\\嵌入式子流程.png");
//         outputStream = new FileOutputStream(file);
//        outputStream.write(IoUtil.readInputStream(inputStream, ""));
//        outputStream.close();
//
//        List<BpmnParseHandler> parseHandlers = getDefaultBpmnParseHandlers();
//        BpmnParseHandlers bpmnParseHandlers = new BpmnParseHandlers();
//        bpmnParseHandlers.addHandlers(parseHandlers);
//        for(Process process : bpmnModel.getProcesses()) {
////            bpmnParseHandlers.parseElement();
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    public void t_2() {
//
//        // BpmnXMLUtil
//        // 创建BpmnModel对象
//        BpmnModel model = new BpmnModel();
//
//        // 创建流程对象
//        Process process = new Process();
//        process.setId("myProcess");
//        model.addProcess(process);
//
//        // 创建开始事件
//        StartEvent startEvent = new StartEvent();
//        startEvent.setId("start");
//        process.addFlowElement(startEvent);
//
//        // 创建用户任务
//        UserTask userTask = new UserTask();
//        userTask.setId("usertask");
//        userTask.setName("User Task");
//        userTask.setAssignee("10011");
//        CustomProperty customProperty = new CustomProperty();
//        customProperty.setId("11");
//        customProperty.setName("sadfsd");
//        customProperty.setSimpleValue("bbb");
//        userTask.getCustomProperties().add(customProperty);
//        FormProperty formProperty = new FormProperty();
//        formProperty.setId("approveNode");
//        formProperty.setName("发起阶段");
//        formProperty.setDefaultExpression("-1");
//        userTask.setFormProperties(Arrays.asList(formProperty));
//        ExtensionAttribute attribute = new ExtensionAttribute();
//        attribute.setName("自定义的属性");
//        attribute.setValue("属性值");
//        userTask.addAttribute(attribute);
//        ActivitiListener listener = new ActivitiListener();
//        listener.setId("listener");
//        listener.setEvent("start");
//        listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION);
//        listener.setImplementation("${xxx}");
//        userTask.getTaskListeners().add(listener);
//        process.addFlowElement(userTask);
//
//        InclusiveGateway exclusiveGateway = new InclusiveGateway();
//        exclusiveGateway.setId(UUID.randomUUID().toString());
//        process.addFlowElement(exclusiveGateway);
//
//        SequenceFlow sequenceFlow1 = new SequenceFlow();
//        sequenceFlow1.setId(UUID.randomUUID().toString());
//        sequenceFlow1.setName("xx");
//        sequenceFlow1.setDocumentation("这是一个连线描述");
//        sequenceFlow1.setSourceRef(startEvent.getId());
//        sequenceFlow1.setTargetRef(exclusiveGateway.getId());
//        process.addFlowElement(sequenceFlow1);
//
//        startEvent.getOutgoingFlows().add(sequenceFlow1);
//        exclusiveGateway.getIncomingFlows().add(sequenceFlow1);
//
//        UserTask userTask2 = new UserTask();
//        userTask2.setId("usertask2");
//        userTask2.setName("User Task2");
//        userTask2.setAssignee("10013");
//        process.addFlowElement(userTask2);
//
//
//        SequenceFlow sequenceFlow2 = new SequenceFlow();
//        sequenceFlow2.setId(UUID.randomUUID().toString());
//        sequenceFlow2.setName("a > 3");
//        sequenceFlow2.setDocumentation("这是一个连线描述");
//        sequenceFlow2.setSourceRef(exclusiveGateway.getId());
//        sequenceFlow2.setTargetRef(userTask.getId());
//        sequenceFlow2.setConditionExpression("${a>3}");
//
//        ActivitiListener activitiListener = new ActivitiListener();
//        activitiListener.setId("activitiListener");
//        activitiListener.setEvent("start");
//        activitiListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION);
//        activitiListener.setImplementation("${yyy}");
//        sequenceFlow2.getExecutionListeners().add(activitiListener);
//        process.addFlowElement(sequenceFlow2);
//
//        exclusiveGateway.getOutgoingFlows().add(sequenceFlow2);
//        userTask2.getIncomingFlows().add(sequenceFlow2);
//
//        SequenceFlow sequenceFlow3 = new SequenceFlow();
//        sequenceFlow3.setId(UUID.randomUUID().toString());
//        sequenceFlow3.setName("a <= 3");
//        sequenceFlow3.setDocumentation("这是一个连线描述");
//        sequenceFlow3.setSourceRef(exclusiveGateway.getId());
//        sequenceFlow3.setTargetRef(userTask2.getId());
//        sequenceFlow3.setConditionExpression("${a<=3}");
//        process.addFlowElement(sequenceFlow3);
//
//
//        exclusiveGateway.getOutgoingFlows().add(sequenceFlow3);
//        userTask2.getIncomingFlows().add(sequenceFlow3);
//
//
//        // 重新布局，用于画流程图图片
//        new BpmnAutoLayout(model).execute();
//
//
//        // 生成BPMN 2.0 XML并写入文件
//        try {
//
//            DefaultProcessDiagramGenerator defaultProcessDiagramGenerator = new DefaultProcessDiagramGenerator();
//            InputStream inputStream = defaultProcessDiagramGenerator.generateDiagram(model, "png", Collections.emptyList());
//            File file = new File("D:\\java_project\\demo\\myProcess.png");
//            FileOutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(IoUtil.readInputStream(inputStream, ""));
//            outputStream.close();
//
//             file = new File("D:\\java_project\\demo\\myProcess.bpmn");
//             outputStream = new FileOutputStream(file);
//            byte[] bytes = new BpmnXMLConverter().convertToXML(model, "UTF-8");
//            outputStream.write(bytes);
//            outputStream.close();
//
//            BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
//            BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(new BytesStreamSource(bytes), false, false);
//
//            System.out.println("BPMN file generated successfully.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    public void tttt() {
////        DateTimeFormatter.ofPattern("yyyy-MM-dd").
//        LocalDate localDate = LocalDate.parse("2023-12-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        LocalDateTime localDateTime = localDate.plusMonths(1).atStartOfDay().withHour(23).withMinute(59).withSecond(59).plusDays(-1);
//        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime));
//        System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//    }
//
//    Pattern pattern1 = Pattern.compile("[0-9]+");
//    Pattern pattern2 = Pattern.compile("[a-zA-Z]+");
//
//    @org.junit.jupiter.api.Test
//    public void t_1() {
//        for (int i = 0; i < 1_0000_0000; i++) {
//            this.randomCharAndDigit();
//        }
//
////        TaskDefinition definition = new TaskDefinition();
//    }
//
//    @org.junit.jupiter.api.Test
//    public void randomCharAndDigit() {
//        // 最短24个字符
//        int length = 32;
//        // 最短24个字符
//        // 随机字符的长度
//        int charLength = RandomUtil.randomInt(1, length);
//        int digitLength = length - charLength;
//        int randomCharCount = 0;
//        int randomDigitCount = 0;
//        char randomChar;
//        StringBuilder stringBuilder = new StringBuilder(length);
//        for (int i = 0; i < length; i++) {
//            if (randomCharCount < charLength && randomDigitCount < digitLength) {
//                if (RandomUtil.randomInt(length) < (length / 2)) {
//                    randomChar = RandomUtil.randomChar(RandomUtil.BASE_CHAR);
//                    int randomUpper = RandomUtil.randomInt(length);
//                    if (randomUpper > (length / 2)) {
//                        randomChar = Character.toUpperCase(randomChar);
//                    }
//                    randomCharCount++;
//                } else {
//                    randomChar = RandomUtil.randomNumber();
//                    randomDigitCount++;
//                }
//            } else {
//                if (randomCharCount < charLength) {
//                    randomChar = RandomUtil.randomChar(RandomUtil.BASE_CHAR);
//                    int randomUpper = RandomUtil.randomInt(length);
//                    if (randomUpper > (length / 2)) {
//                        randomChar = Character.toUpperCase(randomChar);
//                    }
//                    randomCharCount++;
//                } else {
//                    randomChar = RandomUtil.randomNumber();
//                    randomDigitCount++;
//                }
//            }
//
//            stringBuilder.append(randomChar);
//        }
//        if (pattern1.matcher(stringBuilder.toString()).matches()) {
//            System.out.println(stringBuilder);
//        }
//        if (pattern2.matcher(stringBuilder.toString()).matches()) {
//            System.out.println(stringBuilder);
//        }
////        System.out.println(stringBuilder);
////       System.out.println(stringBuilder.length());
//    }
//
//
//    @org.junit.jupiter.api.Test
//    public void t1() throws IOException {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Hessian2Output hessian2Output = new Hessian2Output(out);
//
//        hessian2Output.writeString("hello world!");
//
//        byte[] bytes = out.toByteArray();
//
//        User user = new User();
//        user.setAge(10);
//
//        hessian2Output.writeObject(user);
//
//        user = new User();
//        user.setAge(20);
//
//        hessian2Output.writeObject(user);
//        hessian2Output.flush();
//
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());
//        Hessian2Input hessian2Input = new Hessian2Input(inputStream);
//        String h = hessian2Input.readString();
//        User user1 = (User) hessian2Input.readObject(User.class);
//        User user2 = (User) hessian2Input.readObject();
//        System.out.println(user1);
//
//    }
//
//    @org.junit.jupiter.api.Test
//    public void t() {
//
//        long currentTime = System.currentTimeMillis();
//        LocalDateTime currentLdt = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTime), ZoneId.systemDefault());
//        currentLdt = currentLdt.toLocalDate().atStartOfDay();
//
//        LocalDateTime receiveLdt = LocalDateTime.ofInstant(Instant.ofEpochMilli(1695029163842L),
//            ZoneId.systemDefault());
//        receiveLdt = receiveLdt.toLocalDate().atStartOfDay();
//
//        System.out.println(Duration.between(receiveLdt, currentLdt).toDays());
//    }
//
//    @org.junit.jupiter.api.Test
//    public void xxx() {
//        String pattern =
//            "INSERT INTO `openerp_jyjj_platform`.`tbl_sys_dynamic_expansion` (`name`, `column_name`, `db_name`, `parent_module_code`, `module_code`, `data_type`, `data_accuracy`, `select_value`, `support_search`, `status`, `required`, `order`, `member_id`, `creator_id`, `create_time`, `version`, `last_access`) VALUES "
//                + " ('自定义字段1', 'extendedField1', 'extended_field1', '50', '5001', '2', '0', '', '1', '1', '0', '1', '%s', '0', '1685693191000', '0', '1685693191000'), "
//                + " ('自定义字段2', 'extendedField2', 'extended_field2', '50', '5001', '2', '0', '', '1', '1', '0', '1', '%s', '0', '1685693191000', '0', '1685693191000'), "
//                + " ('自定义字段3', 'extendedField3', 'extended_field3', '50', '5001', '2', '0', '', '1', '1', '0', '1', '%s', '0', '1685693191000', '0', '1685693191000'), "
//                + " ('自定义字段4', 'extendedField4', 'extended_field4', '50', '5001', '2', '0', '', '1', '1', '0', '1', '%s', '0', '1685693191000', '0', '1685693191000'), "
//                + " ('自定义字段5', 'extendedField5', 'extended_field5', '50', '5001', '2', '0', '', '1', '1', '0', '1', '%s', '0', '1685693191000', '0', '1685693191000'), "
//                + " ('自定义字段6', 'extendedField6', 'extended_field6', '50', '5001', '2', '0', '', '1', '1', '0', '1', '%s', '0', '1685693191000', '0', '1685693191000'), "
//                + " ('自定义字段7', 'extendedField7', 'extended_field7', '50', '5001', '2', '0', '', '1', '1', '0', '1', '%s', '0', '1685693191000', '0', '1685693191000'), "
//                + " ('自定义字段8', 'extendedField8', 'extended_field8', '50', '5001', '2', '0', '', '1', '1', '0', '1', '%s', '0', '1685693191000', '0', '1685693191000'), "
//                + " ('自定义字段9', 'extendedField9', 'extended_field9', '50', '5001', '2', '0', '', '1', '1', '0', '1', '%s', '0', '1685693191000', '0', '1685693191000'), "
//                + " ('自定义字段10', 'extendedField10', 'extended_field10', '50', '5001', '2', '0', '', '1', '1', '0', '1', '%s', '0', '1685693191000', '0', '1685693191000');";
//
////        String pattern = "INSERT INTO `openerp_jyjj_platform`.`tbl_sys_global_config` \n"
////            + "(`module`, `module_type`, `module_desc`, `value`, `member_id`, `disabled`, `creator`, `creator_id`, `last_access`, `version`) VALUES \n"
////            + "('0003', '206', '销售订单开单未回款天数设置', '{\\\"expire_days\\\":-1}', '%s', '0', '系统', '0', '1696656955000', '0');\n";
//
////        for (int i = 81330; i <= 81346; i++) {
//        for (int i = 81347; i <= 81351; i++) {
//            System.out.println(pattern.replace("%s", i + ""));
//        }
//
//    }
//
//    class Node {
//
//        private Integer i;
//        private Node next;
//
//        @Override
//        public String toString() {
//            return "i";
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    public void testLink() {
//
//        Node head = null;
//        Node tail = null;
//        for (int i = 0; i < 10; i++) {
//            Node node = new Node();
//            node.i = i;
//            if (Objects.nonNull(tail)) {
//                tail.next = node;
//            }
//            if (Objects.isNull(head)) {
//                head = node;
//            }
//            tail = node;
//        }
//
//        this.transfer(head);
//        this.printLink(tail);
//
//    }
//
//    private void transfer(Node head) {
//        this.digui(head);
//        head.next = null;
//    }
//
//    private Node digui(Node node) {
//        if (Objects.isNull(node.next)) {
//            return node;
//        }
//        Node n = this.digui(node.next);
//        n.next = node;
//        return node;
//    }
//
//    private void printLink(Node head) {
//        Node n = head;
//        do {
//            System.out.println(n.i);
//        } while (Objects.nonNull(n = n.next));
//    }
//
//    @org.junit.jupiter.api.Test
//    public void t2() {
//
//        BigDecimal a = new BigDecimal("199999999999999999999999999999999999999999999999999999999999999999999999999999");
//        BigDecimal b = new BigDecimal("00002");
//
//        System.out.println(a.compareTo(b));
//        System.out.println(a.toPlainString());
//        System.out.println(b.toPlainString());
//
//    }
//
//    @org.junit.jupiter.api.Test
//    public void t3() {
//        // F100099S100001T100001
//        Pattern pattern = Pattern.compile("^(F[0-9]+)S[0-9]+T[0-9]+$");
//        Matcher matcher = pattern.matcher("F100099S100001T100001");
//        if (matcher.matches()) {
//            System.out.println(matcher.group(1));
//        }
//
//    }
//
//    @org.junit.jupiter.api.Test
//    public void t5() {
//        try {
//            URL url = this.getClass().getClassLoader().getResource("");
//            System.out.println(url);
////            File file = new File(uri);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @org.junit.jupiter.api.Test
//    public void t6() {
//        String t = "(\\d{4})(-|/)(\\d{2})(-|/)(\\d{2})\\s+(\\d{2}):(\\d{2}):(\\d{2})";
//        System.out.println("2022/10/11 12:10:09".replaceAll(t, "$1-$3-$5 $6:$7:$8"));
//    }
//
//    protected static List<BpmnParseHandler> getDefaultBpmnParseHandlers() {
//
//        // Alpabetic list of default parse handler classes
//        List<BpmnParseHandler> bpmnParserHandlers = new ArrayList<BpmnParseHandler>();
//        bpmnParserHandlers.add(new BoundaryEventParseHandler());
//        bpmnParserHandlers.add(new BusinessRuleParseHandler());
//        bpmnParserHandlers.add(new CallActivityParseHandler());
//        bpmnParserHandlers.add(new CancelEventDefinitionParseHandler());
//        bpmnParserHandlers.add(new CompensateEventDefinitionParseHandler());
//        bpmnParserHandlers.add(new EndEventParseHandler());
//        bpmnParserHandlers.add(new ErrorEventDefinitionParseHandler());
//        bpmnParserHandlers.add(new EventBasedGatewayParseHandler());
//        bpmnParserHandlers.add(new ExclusiveGatewayParseHandler());
//        bpmnParserHandlers.add(new InclusiveGatewayParseHandler());
//        bpmnParserHandlers.add(new IntermediateCatchEventParseHandler());
//        bpmnParserHandlers.add(new IntermediateThrowEventParseHandler());
//        bpmnParserHandlers.add(new ManualTaskParseHandler());
//        bpmnParserHandlers.add(new MessageEventDefinitionParseHandler());
//        bpmnParserHandlers.add(new ParallelGatewayParseHandler());
//        bpmnParserHandlers.add(new ProcessParseHandler());
//        bpmnParserHandlers.add(new ReceiveTaskParseHandler());
//        bpmnParserHandlers.add(new ScriptTaskParseHandler());
//        bpmnParserHandlers.add(new SendTaskParseHandler());
//        bpmnParserHandlers.add(new SequenceFlowParseHandler());
//        bpmnParserHandlers.add(new ServiceTaskParseHandler());
//        bpmnParserHandlers.add(new SignalEventDefinitionParseHandler());
//        bpmnParserHandlers.add(new StartEventParseHandler());
//        bpmnParserHandlers.add(new SubProcessParseHandler());
//        bpmnParserHandlers.add(new EventSubProcessParseHandler());
//        bpmnParserHandlers.add(new TaskParseHandler());
//        bpmnParserHandlers.add(new TimerEventDefinitionParseHandler());
//        bpmnParserHandlers.add(new TransactionParseHandler());
//        bpmnParserHandlers.add(new UserTaskParseHandler());
//
//        bpmnParserHandlers.addAll(getDefaultHistoryParseHandlers());
//
//        return bpmnParserHandlers;
//    }
//
//    protected static List<BpmnParseHandler> getDefaultHistoryParseHandlers() {
//        List<BpmnParseHandler> parseHandlers = new ArrayList<BpmnParseHandler>();
//        parseHandlers.add(new FlowNodeHistoryParseHandler());
//        parseHandlers.add(new ProcessHistoryParseHandler());
//        parseHandlers.add(new StartEventHistoryParseHandler());
//        parseHandlers.add(new UserTaskHistoryParseHandler());
//        return parseHandlers;
//    }
//}
