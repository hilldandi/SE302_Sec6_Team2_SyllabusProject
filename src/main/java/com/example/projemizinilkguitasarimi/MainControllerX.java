package com.example.projemizinilkguitasarimi;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class MainControllerX implements Initializable {
    @FXML
    private Button OldVerMainButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene3(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("old,.fxml"));
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene4(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CompareSection.fxml"));
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void SelectButton(ActionEvent event) {
        if (file1.getText().isBlank()){
            file1.setText(ListView.getSelectionModel().getSelectedItem());
        }
        else {
            file2.setText(ListView.getSelectionModel().getSelectedItem());
        }

    }
    @FXML
    public String findMyPath(String fileName){
        String selectedItem=fileName;
        String [] lookforlecture=new String[0];
        String selectedkey;
        if (comboBox.getValue()==null){
            lookforlecture=selectedItem.split("-");
            selectedkey=lookforlecture[0];
        }
        else {
            selectedkey=comboBox.getValue();
        }
        String path="Document/";
        String combinedPath=path+selectedkey+"/"+selectedItem;

        return combinedPath;
    }

    @FXML
    void CompareButtonA(ActionEvent event) throws IOException {
        if (file1.getText()=="File 1"||file2.getText()=="File 2"){
            warning.setText("You have to select 2 courses");
        }
        String firstFilePath=findMyPath(file1.getText());
        String secondFilePath=findMyPath(file2.getText());
        ArrayList<ObservableList>listOfList=CompareVersions(firstFilePath,secondFilePath);
        switchToScene4(event);
        ObservableList<String> tab1=FXCollections.observableArrayList(listOfList.get(0));
        Tab1list.setItems(tab1);
        ObservableList<String> tab2=FXCollections.observableArrayList(listOfList.get(1));
        Tab2list.setItems(tab2);
        ObservableList<String> tab3=FXCollections.observableArrayList(listOfList.get(2));
        Tab3list.setItems(tab3);
        ObservableList<String> tab4=FXCollections.observableArrayList(listOfList.get(3));
        Tab4list.setItems(tab4);
        ObservableList<String> tab5=FXCollections.observableArrayList(listOfList.get(4));
        Tab5list.setItems(tab5);
        ObservableList<String> tab6=FXCollections.observableArrayList(listOfList.get(5));
        Tab6list.setItems(tab6);


    }

    @FXML
    public void open(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        String selectedItem=ListView.getSelectionModel().getSelectedItem();
        String [] lookforlecture=new String[0];
        String selectedkey;
        if (comboBox.getValue()==null){
            lookforlecture=selectedItem.split("-");
            selectedkey=lookforlecture[0];

        }
        else {
            selectedkey=comboBox.getValue();
        }

        String path="Document/";
        String combinedPath=path+selectedkey+"/"+selectedItem;
        CourseInformationx course=readJsonFile(combinedPath);
        MainControllerX mainController = fxmlLoader.getController();
        //TAB1
        mainController.courseNameText2.setText(course.getCourseName());
        mainController.codeTextt2.setText(course.getCode());
        mainController.seasontext.setText(course.getSeason());
        mainController.theoryText2.setText(course.getTheory());
        mainController.applicationText2.setText(course.getLab());
        mainController.localCreditsText2.setText(course.getLocalCredits());
        mainController.ectsText2.setText(course.getEcts());
        mainController.prerequisitesText.setText(course.getPrerequisities());
        mainController.CourseLanguageText.setText(course.getCourseLanguage());
        mainController.CourseTypeText.setText(course.getCourseType());
        mainController.CourseLevelText.setText(course.getCourseLevel());
        mainController.ModeOfDeleveryText.setText(course.getModeOfDelivery());
        mainController.teachingMethodsText.setText(course.getTeachingMethodsAndTechniques());
        mainController.coursecoordinatorText.setText(course.getCourseCoordinator());
        mainController.courselecturerText.setText(course.getCourseLecturers());
        mainController.assistantText.setText(course.getCourseAssistants());
        //Tab2
        mainController.courseobjectivesText.setText(course.getCourseObjectives());
        mainController.learningoutcomesText.setText(course.getLearningOutcomes());
        mainController.coursedescriptionText.setText(course.getCourseDescription());
        mainController.coreCourseText.setText(course.getCCCoreCourse());
        mainController.majorAreaCourseText.setText(course.getCCMajorAreaCourse());
        mainController.supportiveCourseText.setText(course.getCCSupportiveCourse());
        mainController.communicationandManagementSkillsCourseText.setText(course.getCCComAndManagementSkillsCourse());
        mainController.transferableSkillCourseText.setText(course.getCCTransferableSkillCourse());

        //Tab3
        mainController.subject1.setText(course.getWeek1Subjects());
        mainController.reqMaterial1.setText(course.getWeek1ReqMat());
        mainController.subject11.setText(course.getWeek2Subjects());
        mainController.reqMaterial11.setText(course.getWeek2ReqMat());
        mainController.subject21.setText(course.getWeek3Subjects());
        mainController.reqMaterial21.setText(course.getWeek3ReqMat());
        mainController.subject31.setText(course.getWeek4Subjects());
        mainController.reqMaterial31.setText(course.getWeek4ReqMat());
        mainController.subject41.setText(course.getWeek5Subjects());
        mainController.reqMaterial41.setText(course.getWeek5ReqMat());
        mainController.subject51.setText(course.getWeek6Subjects());
        mainController.reqMaterial51.setText(course.getWeek6ReqMat());
        mainController.subject71.setText(course.getWeek8Subjects());
        mainController.reqMaterial71.setText(course.getWeek8ReqMat());
        mainController.subject81.setText(course.getWeek9Subjects());
        mainController.reqMaterial81.setText(course.getWeek9ReqMat());
        mainController.subject91.setText(course.getWeek10Subjects());
        mainController.reqMaterial91.setText(course.getWeek10ReqMat());
        mainController.subject101.setText(course.getWeek11Subjects());
        mainController.reqMaterial101.setText(course.getWeek11ReqMat());
        mainController.subject111.setText(course.getWeek12Subjects());
        mainController.reqMaterial111.setText(course.getWeek12ReqMat());
        mainController.subject121.setText(course.getWeek13Subjects());
        mainController.reqMaterial121.setText(course.getWeek13ReqMat());
        mainController.subject131.setText(course.getWeek14Subjects());
        mainController.reqMaterial131.setText(course.getWeek14ReqMat());
        mainController.subject141.setText(course.getWeek15Subjects());
        mainController.reqMaterial141.setText(course.getWeek15ReqMat());
        mainController.courseNotesText.setText(course.getCourseNotesAndTextBooks());
        mainController.suggestedReadingsText.setText(course.getSuggestedReadingsAndMaterials());

        //Tab4
        mainController.participateNumText.setText(course.getNoOfParticipation());
        mainController.participateWText.setText(course.getWeightOfParticipation());
        mainController.participateo1Text.setText(course.getLO1OfParticipation());
        mainController.participateo2Text.setText(course.getLO2OfParticipation());
        mainController.participateo3Text.setText(course.getLO3OfParticipation());
        mainController.participateo4Text.setText(course.getLO4OfParticipation());
        mainController.participateo5Text.setText(course.getLO5OfParticipation());
        mainController.participateo6Text.setText(course.getLO6OfParticipation());
        mainController.participateo7Text.setText(course.getLO7OfParticipation());
        mainController.labNumText.setText(course.getNoOfLabOrApplication());
        mainController.labWText.setText(course.getWeightOfLabOrApplication());
        mainController.labo1Text.setText(course.getLO1OfLabOrApplication());
        mainController.labo2Text.setText(course.getLO2OfLabOrApplication());
        mainController.labo3Text.setText(course.getLO3OfLabOrApplication());
        mainController.labo4Text.setText(course.getLO4OfLabOrApplication());
        mainController.labo5Text.setText(course.getLO5OfLabOrApplication());
        mainController.labo6Text.setText(course.getLO6OfLabOrApplication());
        mainController.labo7Text.setText(course.getLO7OfLabOrApplication());
        mainController.fieldNumText.setText(course.getNoOfFieldWork());
        mainController.fieldWText.setText(course.getWeightOfFieldWork());
        mainController.fieldo1Text.setText(course.getLO1OfFieldWork());
        mainController.fieldo2Text.setText(course.getLO2OfFieldWork());
        mainController.fieldo3Text.setText(course.getLO3OfFieldWork());
        mainController.fieldo4Text.setText(course.getLO4OfFieldWork());
        mainController.fieldo5Text.setText(course.getLO5OfFieldWork());
        mainController.fieldo6Text.setText(course.getLO6OfFieldWork());
        mainController.fieldo7Text.setText(course.getLO7OfFieldWork());
        mainController.quizNumText.setText(course.getNoOfQuizOrStudioCritique());
        mainController.quizWText.setText(course.getWeightOfQuizOrStudioCritique());
        mainController.quizo1Text.setText(course.getLO1OfQuizOrStudioCritique());
        mainController.quizo2Text.setText(course.getLO2OfQuizOrStudioCritique());
        mainController.quizo3Text.setText(course.getLO3OfQuizOrStudioCritique());
        mainController.quizo4Text.setText(course.getLO4OfQuizOrStudioCritique());
        mainController.quizo5Text.setText(course.getLO5OfQuizOrStudioCritique());
        mainController.quizo6Text.setText(course.getLO6OfQuizOrStudioCritique());
        mainController.quiz7Text.setText(course.getLO7OfQuizOrStudioCritique());
        mainController.homeworkNumText.setText(course.getNoOfHwOrAssignment());
        mainController.homeworkWText.setText(course.getWeightOfHwOrAssignment());
        mainController.homeworko1Text.setText(course.getLO1OfHwOrAssignment());
        mainController.homeworko2Text.setText(course.getLO2OfHwOrAssignment());
        mainController.homeworko3Text.setText(course.getLO3OfHwOrAssignment());
        mainController.homeworko4Text.setText(course.getLO4OfHwOrAssignment());
        mainController.homeworko5Text.setText(course.getLO5OfHwOrAssignment());
        mainController.homeworko6Text.setText(course.getLO6OfHwOrAssignment());
        mainController.homeworko7Text.setText(course.getLO7OfHwOrAssignment());
        mainController.juryNumText.setText(course.getNoOfPresentationOrJury());
        mainController.juryWText.setText(course.getWeightOfPresentationOrJury());
        mainController.juryo1Text.setText(course.getLO1OfPresentationOrJury());
        mainController.juryo2Text.setText(course.getLO2OfPresentationOrJury());
        mainController.juryo3Text.setText(course.getLO3OfPresentationOrJury());
        mainController.juryo4Text.setText(course.getLO4OfPresentationOrJury());
        mainController.juryo5Text.setText(course.getLO5OfPresentationOrJury());
        mainController.juryo6Text.setText(course.getLO6OfPresentationOrJury());
        mainController.juryo7Text.setText(course.getLO7OfPresentationOrJury());
        mainController.ProjectNumText.setText(course.getNoOfProject());
        mainController.ProjectNumText.setText(course.getNoOfProject());
        mainController.projectWText.setText(course.getWeightOfProject());
        mainController.projecto1Text.setText(course.getLO1OfProject());
        mainController.projecto2Text.setText(course.getLO2OfProject());
        mainController.projecto3Text.setText(course.getLO3OfProject());
        mainController.projecto4Text.setText(course.getLO4OfProject());
        mainController.projecto5Text.setText(course.getLO5OfProject());
        mainController.projecto6Text.setText(course.getLO6OfProject());
        mainController.projecto7Text.setText(course.getLO7OfProject());
        mainController.seminarNumText.setText(course.getNoOfSeminarOrWorkshop());
        mainController.seminarWText.setText(course.getWeightOfSeminarOrWorkshop());
        mainController.seminaro1Text.setText(course.getLO1OfSeminarOrWorkshop());
        mainController.seminaro2Text.setText(course.getLO2OfSeminarOrWorkshop());
        mainController.seminaro3Text.setText(course.getLO3OfSeminarOrWorkshop());
        mainController.seminaro4Text.setText(course.getLO4OfSeminarOrWorkshop());
        mainController.seminaro5Text.setText(course.getLO5OfSeminarOrWorkshop());
        mainController.seminaro6Text.setText(course.getLO6OfSeminarOrWorkshop());
        mainController.seminaro7Text.setText(course.getLO7OfSeminarOrWorkshop());
        mainController.oralNumText.setText(course.getNoOfOralExam());
        mainController.oralWText.setText(course.getWeightOfOralExam());
        mainController.oralo1Text.setText(course.getLO1OfOralExam());
        mainController.oralo2Text.setText(course.getLO2OfOralExam());
        mainController.oralo3Text.setText(course.getLO3OfOralExam());
        mainController.oralo4Text.setText(course.getLO4OfOralExam());
        mainController.oralo5Text.setText(course.getLO5OfOralExam());
        mainController.oralo6Text.setText(course.getLO6OfOralExam());
        mainController.oralo7Text.setText(course.getLO7OfOralExam());
        mainController.midtermNum.setText(course.getNoOfMidterm());
        mainController.midtermW.setText(course.getWeightOfMidterm());
        mainController.midtermo1Text.setText(course.getLO1OfMidterm());
        mainController.midtermo2Text.setText(course.getLO2OfMidterm());
        mainController.midtermo3Text.setText(course.getLO3OfMidterm());
        mainController.midtermo4Text.setText(course.getLO4OfMidterm());
        mainController.midtermo5Text.setText(course.getLO5OfMidterm());
        mainController.midtermo6Text.setText(course.getLO6OfMidterm());
        mainController.midtermo7Text.setText(course.getLO7OfMidterm());
        mainController.finalNumText.setText(course.getNoOfFinalExam());
        mainController.finalWText.setText(course.getWeightOfFinalExam());
        mainController.finalo1Text.setText(course.getLO1OfFinalExam());
        mainController.finalo2Text.setText(course.getLO2OfFinalExam());
        mainController.finalo3Text.setText(course.getLO3OfFinalExam());
        mainController.finalo4Text.setText(course.getLO4OfFinalExam());
        mainController.finalo5Text.setText(course.getLO5OfFinalExam());
        mainController.finalo6Text.setText(course.getLO6OfFinalExam());
        mainController.finalo7Text.setText(course.getLO7OfFinalExam());
        mainController.totalNumText.setText(course.getNoOfTotalWeight());
        mainController.totalWWText.setText(course.getWeightOfTotalWeight());
        mainController.totalo1Text.setText(course.getLO1OfTotalWeight());
        mainController.totalo2Text.setText(course.getLO2OfTotalWeight());
        mainController.totalo3Text.setText(course.getLO3OfTotalWeight());
        mainController.totalo4Text.setText(course.getLO4OfTotalWeight());
        mainController.totalo5Text.setText(course.getLO5OfTotalWeight());
        mainController.totalo6Text.setText(course.getLO6OfTotalWeight());
        mainController.totalo7Text.setText(course.getLO7OfTotalWeight());
        mainController.finalgrade1.setText(course.getWeightOfSemActivitiesOnFinalGrade1());
        mainController.finalgrade2.setText(course.getWeightOfSemActivitiesOnFinalGrade2());
        mainController.finalEGrade1.setText(course.getWeightOfEndOfSemActivitiesOnFinalGrade1());
        mainController.finalEGrade2.setText(course.getWeightOfEndOfSemActivitiesOnFinalGrade2());
        mainController.totalG1.setText(course.getEndOfTab4Total1());
        mainController.totalG2.setText(course.getEndOfTab4Total2());
        // TAB5
        mainController.cdText.setText(course.getCourseHoursDur());
        mainController.cwText.setText(course.getCourseHoursWL());
        mainController.ldText.setText(course.getLabOrApplicationHoursDur());
        mainController.lwText.setText(course.getLabOrApplicationHourWL());
        mainController.snText.setText(course.getStudyHourseOutsideClass());
        mainController.sdText.setText(course.getStudyHourseOutsideClassDur());
        mainController.swText.setText(course.getStudyHourseOutsideClassWL());
        mainController.fnText.setText(course.getFieldWork());
        mainController.fdText.setText(course.getFieldWorkDur());
        mainController.fwText.setText(course.getFieldWorkWL());
        mainController.qnText1.setText(course.getQuizOrStudioCritique());
        mainController.qdText1.setText(course.getQuizOrStudioCritiqueDur());
        mainController.qwText.setText(course.getStudyHourseOutsideClassWL());
        mainController.hnText.setText(course.getHomeworkOrAssignment());
        mainController.hdText.setText(course.getHomeworkOrAssignmentDur());
        mainController.hwText.setText(course.getHomeworkOrAssignmentWL());
        mainController.prnText.setText(course.getPresentationOrJury());
        mainController.prdText.setText(course.getPresentationOrJuryDur());
        mainController.prwText.setText(course.getPresentationOrJuryWL());
        mainController.pronText.setText(course.getProject());
        mainController.prodText.setText(course.getProjectDur());
        mainController.prowText.setText(course.getProjectWL());
        mainController.portnText.setText(course.getPortfolio());
        mainController.portdText11.setText(course.getPortfolioDur());
        mainController.portwText11.setText(course.getPortfolioWL());
        mainController.semnText.setText(course.getSeminarOrWorkshop());
        mainController.semdText.setText(course.getSeminarOrWorkshopDur());
        mainController.semwText.setText(course.getSeminarOrWorkshopWL());
        mainController.ornText.setText(course.getOralExam());
        mainController.ordText.setText(course.getOralExamDur());
        mainController.orwText.setText(course.getOralExamWL());
        mainController.midnText.setText(course.getMidterm());
        mainController.middText.setText(course.getMidtermDur());
        mainController.midwText.setText(course.getMidtermWL());
        mainController.fnText.setText(course.getFinal());
        mainController.fdText.setText(course.getFinalDur());
        mainController.fwText.setText(course.getFinalWL());
        mainController.totalWWText.setText(course.getTotalWorkload());
        //Tab6
        mainController.CoursePOMText1.setText(course.getProgramCompetenciesOutcome1());
        mainController.CoursePOMText11.setText(course.getProgramCompetenciesOutcome1ContributionLevel1());
        mainController.CoursePOMText12.setText(course.getProgramCompetenciesOutcome1ContributionLevel2());
        mainController.CoursePOMText13.setText(course.getProgramCompetenciesOutcome1ContributionLevel3());
        mainController.CoursePOMText14.setText(course.getProgramCompetenciesOutcome1ContributionLevel4());
        mainController.CoursePOMText15.setText(course.getProgramCompetenciesOutcome1ContributionLevel5());
        mainController.CoursePOMText1LO.setText(course.getProgramCompetenciesOutcome1LOno());
        mainController.CoursePOMText2.setText(course.getProgramCompetenciesOutcome2());
        mainController.CoursePOMText21.setText(course.getProgramCompetenciesOutcome2ContributionLevel1());
        mainController.CoursePOMText22.setText(course.getProgramCompetenciesOutcome2ContributionLevel2());
        mainController.CoursePOMText23.setText(course.getProgramCompetenciesOutcome2ContributionLevel3());
        mainController.CoursePOMText24.setText(course.getProgramCompetenciesOutcome2ContributionLevel4());
        mainController.CoursePOMText25.setText(course.getProgramCompetenciesOutcome2ContributionLevel5());
        mainController.CoursePOMText2LO.setText(course.getProgramCompetenciesOutcome2LOno());
        mainController.CoursePOMText3.setText(course.getProgramCompetenciesOutcome3());
        mainController.CoursePOMText31.setText(course.getProgramCompetenciesOutcome3ContributionLevel1());
        mainController.CoursePOMText32.setText(course.getProgramCompetenciesOutcome3ContributionLevel2());
        mainController.CoursePOMText33.setText(course.getProgramCompetenciesOutcome3ContributionLevel3());
        mainController.CoursePOMText34.setText(course.getProgramCompetenciesOutcome3ContributionLevel4());
        mainController.CoursePOMText35.setText(course.getProgramCompetenciesOutcome3ContributionLevel5());
        mainController.CoursePOMText32LO.setText(course.getProgramCompetenciesOutcome3LOno());
        mainController.CoursePOMText4.setText(course.getProgramCompetenciesOutcome4());
        mainController.CoursePOMText41.setText(course.getProgramCompetenciesOutcome4ContributionLevel1());
        mainController.CoursePOMText42.setText(course.getProgramCompetenciesOutcome4ContributionLevel2());
        mainController.CoursePOMText43.setText(course.getProgramCompetenciesOutcome4ContributionLevel3());
        mainController.CoursePOMText44.setText(course.getProgramCompetenciesOutcome4ContributionLevel4());
        mainController.CoursePOMText45.setText(course.getProgramCompetenciesOutcome4ContributionLevel5());
        mainController.CoursePOMText4LO.setText(course.getProgramCompetenciesOutcome4LOno());
        mainController.CoursePOMText5.setText(course.getProgramCompetenciesOutcome5());
        mainController.CoursePOMText51.setText(course.getProgramCompetenciesOutcome5ContributionLevel1());
        mainController.CoursePOMText52.setText(course.getProgramCompetenciesOutcome5ContributionLevel2());
        mainController.CoursePOMText53.setText(course.getProgramCompetenciesOutcome5ContributionLevel3());
        mainController.CoursePOMText54.setText(course.getProgramCompetenciesOutcome5ContributionLevel4());
        mainController.CoursePOMText55.setText(course.getProgramCompetenciesOutcome5ContributionLevel5());
        mainController.CoursePOMText5LO.setText(course.getProgramCompetenciesOutcome5LOno());
        mainController.CoursePOMText6.setText(course.getProgramCompetenciesOutcome6());
        mainController.CoursePOMText61.setText(course.getProgramCompetenciesOutcome6ContributionLevel1());
        mainController.CoursePOMText62.setText(course.getProgramCompetenciesOutcome6ContributionLevel2());
        mainController.CoursePOMText63.setText(course.getProgramCompetenciesOutcome6ContributionLevel3());
        mainController.CoursePOMText64.setText(course.getProgramCompetenciesOutcome6ContributionLevel4());
        mainController.CoursePOMText65.setText(course.getProgramCompetenciesOutcome6ContributionLevel5());
        mainController.CoursePOMText6LO.setText(course.getProgramCompetenciesOutcome6LOno());
        mainController.CoursePOMText7.setText(course.getProgramCompetenciesOutcome7());
        mainController.CoursePOMText71.setText(course.getProgramCompetenciesOutcome7ContributionLevel1());
        mainController.CoursePOMText72.setText(course.getProgramCompetenciesOutcome7ContributionLevel2());
        mainController.CoursePOMText73.setText(course.getProgramCompetenciesOutcome7ContributionLevel3());
        mainController.CoursePOMText74.setText(course.getProgramCompetenciesOutcome7ContributionLevel4());
        mainController.CoursePOMText75.setText(course.getProgramCompetenciesOutcome7ContributionLevel5());
        mainController.CoursePOMText7LO.setText(course.getProgramCompetenciesOutcome7LOno());
        mainController.CoursePOMText8.setText(course.getProgramCompetenciesOutcome8());
        mainController.CoursePOMText81.setText(course.getProgramCompetenciesOutcome8ContributionLevel1());
        mainController.CoursePOMText82.setText(course.getProgramCompetenciesOutcome8ContributionLevel2());
        mainController.CoursePOMText83.setText(course.getProgramCompetenciesOutcome8ContributionLevel3());
        mainController.CoursePOMText84.setText(course.getProgramCompetenciesOutcome8ContributionLevel4());
        mainController.CoursePOMText85.setText(course.getProgramCompetenciesOutcome8ContributionLevel5());
        mainController.CoursePOMText8LO.setText(course.getProgramCompetenciesOutcome8LOno());
        mainController.CoursePOMText9.setText(course.getProgramCompetenciesOutcome9());
        mainController.CoursePOMText91.setText(course.getProgramCompetenciesOutcome9ContributionLevel1());
        mainController.CoursePOMText92.setText(course.getProgramCompetenciesOutcome9ContributionLevel2());
        mainController.CoursePOMText93.setText(course.getProgramCompetenciesOutcome9ContributionLevel3());
        mainController.CoursePOMText94.setText(course.getProgramCompetenciesOutcome9ContributionLevel4());
        mainController.CoursePOMText95.setText(course.getProgramCompetenciesOutcome9ContributionLevel5());
        mainController.CoursePOMText9LO.setText(course.getProgramCompetenciesOutcome9LOno());
        mainController.CoursePOMText10.setText(course.getProgramCompetenciesOutcome10());
        mainController.CoursePOMText101.setText(course.getProgramCompetenciesOutcome10ContributionLevel1());
        mainController.CoursePOMText102.setText(course.getProgramCompetenciesOutcome10ContributionLevel2());
        mainController.CoursePOMText103.setText(course.getProgramCompetenciesOutcome10ContributionLevel3());
        mainController.CoursePOMText104.setText(course.getProgramCompetenciesOutcome10ContributionLevel4());
        mainController.CoursePOMText105.setText(course.getProgramCompetenciesOutcome10ContributionLevel5());
        mainController.CoursePOMText10LO.setText(course.getProgramCompetenciesOutcome10LOno());
        mainController.CoursePOMText110.setText(course.getProgramCompetenciesOutcome11());
        mainController.CoursePOMText111.setText(course.getProgramCompetenciesOutcome11ContributionLevel1());
        mainController.CoursePOMText112.setText(course.getProgramCompetenciesOutcome11ContributionLevel2());
        mainController.CoursePOMText113.setText(course.getProgramCompetenciesOutcome11ContributionLevel3());
        mainController.CoursePOMText114.setText(course.getProgramCompetenciesOutcome11ContributionLevel4());
        mainController.CoursePOMText115.setText(course.getProgramCompetenciesOutcome11ContributionLevel5());
        mainController.CoursePOMText11LO.setText(course.getProgramCompetenciesOutcome11LOno());
        mainController.CoursePOMText120.setText(course.getProgramCompetenciesOutcome12());
        mainController.CoursePOMText121.setText(course.getProgramCompetenciesOutcome12ContributionLevel1());
        mainController.CoursePOMText122.setText(course.getProgramCompetenciesOutcome12ContributionLevel2());
        mainController.CoursePOMText123.setText(course.getProgramCompetenciesOutcome12ContributionLevel3());
        mainController.CoursePOMText124.setText(course.getProgramCompetenciesOutcome12ContributionLevel4());
        mainController.CoursePOMText125.setText(course.getProgramCompetenciesOutcome12ContributionLevel5());
        mainController.CoursePOMText12LO.setText(course.getProgramCompetenciesOutcome12LOno());
        mainController.CoursePOMText130.setText(course.getProgramCompetenciesOutcome13());
        mainController.CoursePOMText131.setText(course.getProgramCompetenciesOutcome13ContributionLevel1());
        mainController.CoursePOMText132.setText(course.getProgramCompetenciesOutcome13ContributionLevel2());
        mainController.CoursePOMText133.setText(course.getProgramCompetenciesOutcome13ContributionLevel3());
        mainController.CoursePOMText134.setText(course.getProgramCompetenciesOutcome13ContributionLevel4());
        mainController.CoursePOMText135.setText(course.getProgramCompetenciesOutcome13ContributionLevel5());
        mainController.CoursePOMText13LO.setText(course.getProgramCompetenciesOutcome13LOno());
        //TAB SAVE
        mainController.SaveAllText.setText(course.getReasonForUpdate());

        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void HelpButton(ActionEvent event) {

    }

    @FXML
    void trButtonA(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-viewTurkish.fxml"));
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void engButtonA(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void SaveAll(ActionEvent event) throws IOException {
        CreateNewCourse();
        switchToScene2(event);
    }

    @FXML
    public void search(ActionEvent event){
        comboBox.setValue(null);
        String path="Document/";
        fileMap=GetFiles();
        String wordToFind=searchText.getText().toUpperCase();
        String vals;
        ArrayList<String> searched_Val=new ArrayList<>();
        for (int i=0;i<Keys.length;i++) {
            if (Keys[i].toUpperCase().contains(wordToFind)) {
                vals = (String) fileMap.get(Keys[i]);
                searched_Val.addAll(List.of(vals.split(",")));
            }
        }

        ObservableList<String> searchedval = FXCollections.observableArrayList(searched_Val);
        ListView.setItems(searchedval);


    }

    @FXML
    void look(ActionEvent event)throws IOException{
        fileMap=GetFiles();
        String vals;
        ArrayList<String> Valuesx = new ArrayList<>();

        if (comboBox.getValue()==null){
            for (int i=0;i<Keys.length;i++){
                vals=(String) fileMap.get(Keys[i]);
                Valuesx.addAll(List.of(vals.split(","))) ;
            }
            ObservableList<String> value = FXCollections.observableArrayList(Valuesx);
            ListView.setItems(value);

        }else {
            vals = (String) fileMap.get(comboBox.getValue());
            values = vals.split(",");
            ObservableList<String> value = FXCollections.observableArrayList(values);
            ListView.setItems(value);
        }


    }

    @FXML
    void delete(ActionEvent event) throws IOException {
        String path = "Document/";
        String selectedDeletingLecture = comboBox.getValue();
        String selectedDeletingFile = ListView.getSelectionModel().getSelectedItem();
        String combinedPath = path + selectedDeletingLecture + "/" + selectedDeletingFile;
        File deleteFile = new File(combinedPath);
        File parentFile = deleteFile.getParentFile();
        deleteFile.delete();
        if (parentFile.list().length == 0) {
            parentFile.delete();
        }


    }

    public HashMap<String, String> GetFiles() {
        String path = "Document/";
        String combinedPath = path;
        File directory = new File(path);
        Keys = directory.list();
        HashMap<String, String> jsonMap = new HashMap<>();
        for (String member : Keys) {
            File dir = new File(path + member);
            String[] innerList = dir.list();
            String innerObject = "";
            for (int i = 0; i < innerList.length; i++) {
                if (i == innerList.length - 1) {
                    innerObject = innerObject + innerList[i];
                } else {
                    innerObject = innerObject + innerList[i] + ",";
                }
            }
            jsonMap.put(member, innerObject);
        }
        return jsonMap;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setOnMouseClicked(e -> onAction());

    }

    @FXML
    public void onAction()  {
        fileMap=GetFiles();
        ObservableList<String> key= FXCollections.observableArrayList(Keys);
        comboBox.setItems(key);
        String vals;
        ArrayList<String> Valuesx = new ArrayList<>();
        if (comboBox.getValue()==null){
            for (int i=0;i<Keys.length;i++){
                vals=(String) fileMap.get(Keys[i]);
                Valuesx.addAll(List.of(vals.split(","))) ;
            }
            ObservableList<String> value = FXCollections.observableArrayList(Valuesx);
            ListView.setItems(value);
        }


    }


    private static CourseInformationx readJsonFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            return new Gson().fromJson(fileReader, CourseInformationx.class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating a new object.");
            return new CourseInformationx();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ObservableList> CompareVersions (String filePathOfOldVersion, String filePathOfNewVersion)throws IOException{

        CourseInformationx oldVersion, newVersion;
        oldVersion = readJsonFile(filePathOfOldVersion);
        newVersion = readJsonFile(filePathOfNewVersion);
        ArrayList<ObservableList> listOfList=new ArrayList<>();

        //declaring String arrays of tabs to compare
        String[] oldtab1 = oldVersion.getTAB1();
        String[] newtab1 = newVersion.getTAB1();
        ArrayList<String> vChanges1=new ArrayList<>();

        String[] oldtab2 = oldVersion.getTAB2();
        String[] newtab2 = newVersion.getTAB2();
        ArrayList<String> vChanges2=new ArrayList<>();

        String[] oldtab3 = oldVersion.getTAB3();
        String[] newtab3 = newVersion.getTAB3();
        ArrayList<String> vChanges3=new ArrayList<>();

        String[] oldtab4 = oldVersion.getTAB4();
        String[] newtab4 = newVersion.getTAB4();
        ArrayList<String> vChanges4=new ArrayList<>();

        String[] oldtab5 = oldVersion.getTAB5();
        String[] newtab5 = newVersion.getTAB5();
        ArrayList<String> vChanges5=new ArrayList<>();

        String[] oldtab6 = oldVersion.getTAB6();
        String[] newtab6 = newVersion.getTAB6();
        ArrayList<String> vChanges6=new ArrayList<>();

        //comparing tabs. might implement Threads to make this process faster :)
        //has some unused variables inside.
        for (int i = 0; i < oldtab1.length; i++) {
            if (!newtab1[i].equals(oldtab1[i])) {
                vChanges1.add(newtab1[i]);
            }
        }
        for (int i = 0; i < oldtab2.length; i++) {
            if (!newtab2[i].equals(oldtab2[i])) {
                vChanges1.add(newtab1[i]);
            }
        }
        for (int i = 0; i < oldtab3.length; i++) {
            if (!newtab3[i].equals(oldtab3[i])) {
                vChanges3.add(newtab3[i]);
            }
        }
        for (int i = 0; i < oldtab4.length; i++) {
            if (!newtab4[i].equals(oldtab4[i])) {
                vChanges4.add(newtab3[i]);
            }
        }
        for (int i = 0; i < oldtab5.length; i++) {
            if (!newtab5[i].equals(oldtab5[i])) {
                vChanges5.add(newtab3[i]);
            }
        }
        for (int i = 0; i < oldtab6.length; i++) {
            if (!newtab6[i].equals(oldtab6[i])) {
                vChanges6.add(newtab3[i]);
            }
        }
        ObservableList<String> tab1= FXCollections.observableArrayList(vChanges1);
        listOfList.add(tab1);
        ObservableList<String> tab2= FXCollections.observableArrayList(vChanges2);
        listOfList.add(tab2);
        ObservableList<String> tab3= FXCollections.observableArrayList(vChanges3);
        listOfList.add(tab3);
        ObservableList<String> tab4= FXCollections.observableArrayList(vChanges4);
        listOfList.add(tab4);
        ObservableList<String> tab5= FXCollections.observableArrayList(vChanges5);
        listOfList.add(tab5);
        ObservableList<String> tab6= FXCollections.observableArrayList(vChanges6);
        listOfList.add(tab6);
        return listOfList;
        /* if all else fails
                                    // comparing new version to old version's TAB1
        if (!newVersion.getCourseName().equals(oldVersion.getCourseName())) {

        }

        if (!newVersion.getCode().equals(oldVersion.getCode())) {

        }

        if (!newVersion.getSeason().equals(oldVersion.getSeason())) {

        }

        if (!newVersion.getSeasonFall().equals(oldVersion.getSeasonFall())) {

        }

        if (!newVersion.getSeasonSpring().equals(oldVersion.getSeasonSpring())) {

        }

        if (!newVersion.getTheory().equals(oldVersion.getTheory())) {

        }

        if (!newVersion.getLab().equals(oldVersion.getLab())) {

        }

        if (!newVersion.getLocalCredits().equals(oldVersion.getLocalCredits())) {

        }

        if (!newVersion.getEcts().equals(oldVersion.getEcts())) {

        }

        if (!newVersion.getPrerequisities().equals(oldVersion.getPrerequisities())) {

        }

        if (!newVersion.getCourseLanguage().equals(oldVersion.getCourseLanguage())) {

        }

        if (!newVersion.getCourseType().equals(oldVersion.getCourseType())) {

        }

        if (!newVersion.getCourseLevel().equals(oldVersion.getCourseLevel())) {

        }

        if (!newVersion.getModeOfDelivery().equals(oldVersion.getModeOfDelivery())) {

        }

        if (!newVersion.getTeachingMethodsAndTechniques().equals(oldVersion.getTeachingMethodsAndTechniques())) {

        }

        if (!newVersion.getCourseCoordinator().equals(oldVersion.getCourseCoordinator())) {

        }

        if (!newVersion.getCourseLecturers().equals(oldVersion.getCourseLecturers())) {

        }

        if (!newVersion.getCourseAssistants().equals(oldVersion.getCourseAssistants())) {

        }

                        //TAB2

                        //TAB3

                        //TAB4

                        //TAB5

                        //TAB6

                        //LAST TAB
    */
}

    /*@Test
    public void CompareVersionsTAB1Test(){// works
        String filePathOfOldVersion = "Document/CE323/CE323.json";
        String filePathOfNewVersion = "Document/CE323v1/CE323v1.json";
        int noOfMismatches = 0;

        CourseInformationx oldVersion, newVersion;
        oldVersion = readJsonFile(filePathOfOldVersion);
        newVersion = readJsonFile(filePathOfNewVersion);

        //declaring String arrays of tabs to compare
        String[] oldtab1 = oldVersion.getTAB1();
        String[] newtab1 = newVersion.getTAB1();
        String[] vChanges1 = new String[oldtab1.length]; // not sure if this works

        String[] oldtab2 = oldVersion.getTAB2();
        String[] newtab2 = newVersion.getTAB2();
        String[] vChanges2 = new String[oldtab2.length];

        String[] oldtab3 = oldVersion.getTAB3();
        String[] newtab3 = newVersion.getTAB3();
        String[] vChanges3 = new String[oldtab3.length];

        String[] oldtab4 = oldVersion.getTAB4();
        String[] newtab4 = newVersion.getTAB4();
        String[] vChanges4 = new String[oldtab4.length];

        String[] oldtab5 = oldVersion.getTAB5();
        String[] newtab5 = newVersion.getTAB5();
        String[] vChanges5 = new String[oldtab5.length];

        String[] oldtab6 = oldVersion.getTAB6();
        String[] newtab6 = newVersion.getTAB6();
        String[] vChanges6 = new String[oldtab6.length];

        //comparing tabs. might implement Threads to make this process faster :)
        //has some unused variables inside.
        for (int i = 0; i < oldtab1.length; i++) {
            if (!newtab1[i].equals(oldtab1[i])) {
                vChanges1[i] = newtab1[i];
                noOfMismatches++;
            }
        }
        for (int i = 0; i < oldtab2.length; i++) {
            if (!newtab2[i].equals(oldtab2[i])) {
                vChanges2[i] = newtab2[i];
                noOfMismatches++;
            }
        }
        for (int i = 0; i < oldtab3.length; i++) {
            if (!newtab3[i].equals(oldtab3[i])) {
                vChanges3[i] = newtab3[i];
                noOfMismatches++;
            }
        }
        for (int i = 0; i < oldtab4.length; i++) {
            if (!newtab4[i].equals(oldtab4[i])) {
                vChanges4[i] = newtab4[i];
                noOfMismatches++;
            }
        }
        for (int i = 0; i < oldtab5.length; i++) {
            if (!newtab5[i].equals(oldtab5[i])) {
                vChanges5[i] = newtab5[i];
                noOfMismatches++;
            }
        }
        for (int i = 0; i < oldtab6.length; i++) {
            if (!newtab6[i].equals(oldtab6[i])) {
                vChanges6[i] = newtab6[i];
                noOfMismatches++;
            }
        }
        assertEquals(noOfMismatches , 11); //there should be 12 missmatches
    }*/
    @FXML
    public void CreateNewCourse()throws IOException {
        Gson gson = new Gson();
        String path = "Document/";
        String code =codeTextt2.getText().toUpperCase();
        String combinedPath = path + code.toUpperCase();
        String newFilePath;

        File directory=new File(combinedPath);
        if (directory.exists()){

            int lastVersion=versionCheck(combinedPath)+1;
            newFilePath = code +"-V"+lastVersion+ ".json";
        }
        else{
            newFilePath= code+"-V0.json";
        }
        CourseInformationx course=new CourseInformationx();
        fillCourse(course);
        String newJson = gson.toJson(course);



        //Create new directory according to course code
        File dir = new File(combinedPath);
        dir.mkdirs();
        File file = new File(dir, newFilePath);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(newJson);
            System.out.println("JSON written to file successfully.");
        }
    }
    public int versionCheck(String path){

        File directory = new File(path);
        String[] list = directory.list();
        String[] versions = new String[0];
        for (String member : list) {
            versions =member.split("-V");
        }
        String lastVersion=versions[1];
        int lastChar=0;
        if (lastVersion.length()==6){
            lastChar = Integer.parseInt(lastVersion.substring(lastVersion.length() -6,lastVersion.length()-5));
        } else if (lastVersion.length()==7) {
            lastChar = Integer.parseInt(lastVersion.substring(lastVersion.length() -7,lastVersion.length()-5));
        } else if (lastVersion.length()==8) {
            lastChar = Integer.parseInt(lastVersion.substring(lastVersion.length() -8,lastVersion.length()-5));
        }

        System.out.println(lastChar);
        System.out.println(lastVersion);
        return lastChar;
    }

    @FXML
    public void fillCourse (CourseInformationx course){
            //Tab1
            course.setCourseName(courseNameText2.getText());
            course.setCode(codeTextt2.getText());
            course.setSeason(seasontext.getText());
            course.setTheory(theoryText2.getText());
            course.setLab(applicationText2.getText());
            course.setLocalCredits(localCreditsText2.getText());
            course.setEcts(ectsText2.getText());
            course.setPrerequisities(prerequisitesText.getText());
            course.setCourseLanguage(CourseLanguageText.getText());
            course.setCourseType(CourseTypeText.getText());
            course.setCourseLevel(CourseLevelText.getText());
            course.setModeOfDelivery(ModeOfDeleveryText.getText());
            course.setTeachingMethodsAndTechniques(teachingMethodsText.getText());
            course.setCourseCoordinator(coursecoordinatorText.getText());
            course.setCourseLecturers(courselecturerText.getText());
            course.setCourseAssistants(assistantText.getText());
        String[] TAB1 = {
                courseNameText2.getText(),codeTextt2.getText(),seasontext.getText(),theoryText2.getText(),
                applicationText2.getText(),localCreditsText2.getText(),ectsText2.getText(),prerequisitesText.getText(),
                CourseLanguageText.getText(),CourseTypeText.getText(),CourseLevelText.getText(),
                ModeOfDeleveryText.getText(),teachingMethodsText.getText(),coursecoordinatorText.getText(),
                courselecturerText.getText(), assistantText.getText()
        };
            course.setTAB1(TAB1);
            //Tab2
            course.setCourseObjectives(courseobjectivesText.getText());
            course.setLearningOutcomes(learningoutcomesText.getText());
            course.setCourseDescription(coursedescriptionText.getText());
            course.setCCCoreCourse(coreCourseText.getText());
            course.setCCMajorAreaCourse(majorAreaCourseText.getText());
            course.setCCSupportiveCourse(supportiveCourseText.getText());
            course.setCCComAndManagementSkillsCourse(communicationandManagementSkillsCourseText.getText());
            course.setCCTransferableSkillCourse(transferableSkillCourseText.getText());
            String[] TAB2 = {courseobjectivesText.getText(),learningoutcomesText.getText(),
                    coursedescriptionText.getText(),coreCourseText.getText(),majorAreaCourseText.getText(),
                    supportiveCourseText.getText(),communicationandManagementSkillsCourseText.getText(),
                    transferableSkillCourseText.getText()};
            course.setTAB2(TAB2);
            //Tab3
            course.setWeek1Subjects(subject1.getText());
            course.setWeek1ReqMat(reqMaterial1.getText());
            course.setWeek2Subjects(subject11.getText());
            course.setWeek2ReqMat(reqMaterial11.getText());
            course.setWeek3Subjects(subject21.getText());
            course.setWeek3ReqMat(reqMaterial21.getText());
            course.setWeek4Subjects(subject31.getText());
            course.setWeek4ReqMat(reqMaterial31.getText());
            course.setWeek5Subjects(subject41.getText());
            course.setWeek5ReqMat(reqMaterial41.getText());
            course.setWeek6Subjects(subject51.getText());
            course.setWeek6ReqMat(reqMaterial51.getText());
            course.setWeek7Subjects(subject61.getText());
            course.setWeek7ReqMat(reqMaterial61.getText());
            course.setWeek8Subjects(subject71.getText());
            course.setWeek8ReqMat(reqMaterial71.getText());
            course.setWeek9Subjects(subject81.getText());
            course.setWeek9ReqMat(reqMaterial81.getText());
            course.setWeek10Subjects(subject91.getText());
            course.setWeek10ReqMat(reqMaterial91.getText());
            course.setWeek11Subjects(subject101.getText());
            course.setWeek11ReqMat(reqMaterial101.getText());
            course.setWeek12Subjects(subject111.getText());
            course.setWeek12ReqMat(reqMaterial111.getText());
            course.setWeek13Subjects(subject121.getText());
            course.setWeek13ReqMat(reqMaterial121.getText());
            course.setWeek14Subjects(subject131.getText());
            course.setWeek14ReqMat(reqMaterial131.getText());
            course.setWeek15Subjects(subject141.getText());
            course.setWeek15ReqMat(reqMaterial141.getText());
            course.setCourseNotesAndTextBooks(courseNotesText.getText());
            course.setSuggestedReadingsAndMaterials(suggestedReadingsText.getText());
        String[] TAB3 = {
                subject1.getText(), reqMaterial1.getText(),subject11.getText(), reqMaterial11.getText(),
                subject21.getText(), reqMaterial21.getText(),subject31.getText(), reqMaterial31.getText(),
                subject41.getText(), reqMaterial41.getText(),subject51.getText(), reqMaterial51.getText(),
                subject61.getText(), reqMaterial61.getText(),subject71.getText(), reqMaterial71.getText(),
                subject81.getText(), reqMaterial81.getText(),subject91.getText(), reqMaterial91.getText(),
                subject101.getText(), reqMaterial101.getText(),subject111.getText(), reqMaterial111.getText(),
                subject121.getText(), reqMaterial121.getText(),subject131.getText(), reqMaterial131.getText(),
                subject141.getText(), reqMaterial141.getText(),
        };
            course.setTAB3(TAB3);
        //Tab4
            course.setNoOfParticipation(participateNumText.getText());
            course.setWeightOfParticipation(participateWText.getText());
            course.setLO1OfParticipation(participateo1Text.getText());
            course.setLO2OfParticipation(participateo2Text.getText());
            course.setLO3OfParticipation(participateo3Text.getText());
            course.setLO4OfParticipation(participateo4Text.getText());
            course.setLO5OfParticipation(participateo5Text.getText());
            course.setLO6OfParticipation(participateo6Text.getText());
            course.setLO7OfParticipation(participateo7Text.getText());
            course.setNoOfLabOrApplication(labNumText.getText());
            course.setWeightOfLabOrApplication(labWText.getText());
            course.setLO1OfLabOrApplication(labo1Text.getText());
            course.setLO2OfLabOrApplication(labo2Text.getText());
            course.setLO3OfLabOrApplication(labo3Text.getText());
            course.setLO4OfLabOrApplication(labo4Text.getText());
            course.setLO5OfLabOrApplication(labo5Text.getText());
            course.setLO6OfLabOrApplication(labo6Text.getText());
            course.setLO7OfLabOrApplication(labo7Text.getText());
            course.setNoOfFieldWork(fieldNumText.getText());
            course.setWeightOfFieldWork(fieldWText.getText());
            course.setLO1OfFieldWork(fieldo1Text.getText());
            course.setLO2OfFieldWork(fieldo2Text.getText());
            course.setLO3OfLabOrApplication(fieldo3Text.getText());
            course.setLO4OfLabOrApplication(fieldo4Text.getText());
            course.setLO5OfLabOrApplication(fieldo3Text.getText());
            course.setLO6OfLabOrApplication(fieldo6Text.getText());
            course.setLO7OfLabOrApplication(fieldo7Text.getText());
            course.setNoOfQuizOrStudioCritique(quizNumText.getText());
            course.setWeightOfQuizOrStudioCritique(quizWText.getText());
            course.setLO1OfQuizOrStudioCritique(quizo1Text.getText());
            course.setLO2OfQuizOrStudioCritique(quizo2Text.getText());
            course.setLO3OfQuizOrStudioCritique(quizo3Text.getText());
            course.setLO4OfQuizOrStudioCritique(quizo4Text.getText());
            course.setLO5OfQuizOrStudioCritique(quizo5Text.getText());
            course.setLO6OfQuizOrStudioCritique(quizo6Text.getText());
            course.setLO7OfQuizOrStudioCritique(quiz7Text.getText());
            course.setNoOfHwOrAssignment(homeworkNumText.getText());
            course.setWeightOfHwOrAssignment(homeworkWText.getText());
            course.setLO1OfHwOrAssignment(homeworko1Text.getText());
            course.setLO2OfHwOrAssignment(homeworko2Text.getText());
            course.setLO3OfHwOrAssignment(homeworko3Text.getText());
            course.setLO4OfHwOrAssignment(homeworko4Text.getText());
            course.setLO5OfHwOrAssignment(homeworko5Text.getText());
            course.setLO6OfHwOrAssignment(homeworko6Text.getText());
            course.setLO7OfHwOrAssignment(homeworko7Text.getText());
            course.setNoOfPresentationOrJury(juryNumText.getText());
            course.setWeightOfPresentationOrJury(juryWText.getText());
            course.setLO1OfPresentationOrJury(juryo1Text.getText());
            course.setLO2OfPresentationOrJury(juryo2Text.getText());
            course.setLO3OfPresentationOrJury(juryo3Text.getText());
            course.setLO4OfPresentationOrJury(juryo4Text.getText());
            course.setLO5OfPresentationOrJury(juryo5Text.getText());
            course.setLO6OfPresentationOrJury(juryo6Text.getText());
            course.setLO7OfPresentationOrJury(juryo6Text.getText());
            course.setNoOfProject(ProjectNumText.getText());
            course.setWeightOfProject(projectWText.getText());
            course.setLO1OfProject(projecto1Text.getText());
            course.setLO2OfProject(projecto2Text.getText());
            course.setLO3OfProject(projecto3Text.getText());
            course.setLO4OfProject(projecto4Text.getText());
            course.setLO5OfProject(projecto5Text.getText());
            course.setLO6OfProject(projecto6Text.getText());
            course.setLO7OfProject(projecto7Text.getText());
            course.setNoOfSeminarOrWorkshop(seminarNumText.getText());
            course.setWeightOfSeminarOrWorkshop(seminarWText.getText());
            course.setLO1OfSeminarOrWorkshop(seminaro1Text.getText());
            course.setLO2OfSeminarOrWorkshop(seminaro2Text.getText());
            course.setLO3OfSeminarOrWorkshop(seminaro3Text.getText());
            course.setLO4OfSeminarOrWorkshop(seminaro4Text.getText());
            course.setLO5OfSeminarOrWorkshop(seminaro5Text.getText());
            course.setLO6OfSeminarOrWorkshop(seminaro6Text.getText());
            course.setLO7OfSeminarOrWorkshop(seminaro7Text.getText());
            course.setNoOfOralExam(oralNumText.getText());
            course.setWeightOfOralExam(oralWText.getText());
            course.setLO1OfOralExam(oralo1Text.getText());
            course.setLO2OfOralExam(oralo2Text.getText());
            course.setLO3OfOralExam(oralo3Text.getText());
            course.setLO4OfOralExam(oralo4Text.getText());
            course.setLO5OfOralExam(oralo5Text.getText());
            course.setLO6OfOralExam(oralo6Text.getText());
            course.setLO7OfOralExam(oralo7Text.getText());
            course.setNoOfMidterm(midtermNum.getText());
            course.setWeightOfMidterm(midtermW.getText());
            course.setLO1OfMidterm(midtermo1Text.getText());
            course.setLO2OfMidterm(midtermo2Text.getText());
            course.setLO3OfMidterm(midtermo3Text.getText());
            course.setLO4OfMidterm(midtermo4Text.getText());
            course.setLO5OfMidterm(midtermo5Text.getText());
            course.setLO6OfMidterm(midtermo6Text.getText());
            course.setLO7OfMidterm(midtermo7Text.getText());
            course.setNoOfFinalExam(finalNumText.getText());
            course.setWeightOfFinalExam(finalWText.getText());
            course.setLO1OfFinalExam(finalo1Text.getText());
            course.setLO2OfFinalExam(finalo2Text.getText());
            course.setLO3OfFinalExam(finalo3Text.getText());
            course.setLO4OfFinalExam(finalo4Text.getText());
            course.setLO5OfFinalExam(finalo5Text.getText());
            course.setLO6OfFinalExam(finalo6Text.getText());
            course.setLO7OfFinalExam(finalo7Text.getText());
            course.setNoOfTotalWeight(totalNumText.getText());
            course.setWeightOfTotalWeight(totalWWText.getText());
            course.setLO1OfTotalWeight(totalo1Text.getText());
            course.setLO2OfTotalWeight(totalo2Text.getText());
            course.setLO3OfTotalWeight(totalo3Text.getText());
            course.setLO4OfTotalWeight(totalo4Text.getText());
            course.setLO5OfTotalWeight(totalo5Text.getText());
            course.setLO6OfTotalWeight(totalo6Text.getText());
            course.setLO7OfTotalWeight(totalo7Text.getText());
            course.setWeightOfSemActivitiesOnFinalGrade1(finalgrade1.getText());
            course.setWeightOfSemActivitiesOnFinalGrade2(finalgrade2.getText());
            course.setWeightOfEndOfSemActivitiesOnFinalGrade1(finalEGrade1.getText());
            course.setWeightOfEndOfSemActivitiesOnFinalGrade2(finalEGrade2.getText());
            course.setEndOfTab4Total1(totalG1.getText());
            course.setEndOfTab4Total2(totalG2.getText());
        String[] TAB4 = {
                participateNumText.getText(),participateWText.getText(),participateo1Text.getText(),
                participateo2Text.getText(),participateo3Text.getText(),participateo4Text.getText(),
                participateo5Text.getText(),participateo6Text.getText(),participateo7Text.getText(),labNumText.getText(),
                labWText.getText(),labo1Text.getText(),labo2Text.getText(),labo3Text.getText(),labo4Text.getText(),
                labo5Text.getText(),labo6Text.getText(),labo7Text.getText(),fieldNumText.getText(),fieldWText.getText(),
                fieldo1Text.getText(),fieldo2Text.getText(),fieldo3Text.getText(),fieldo4Text.getText(),
                fieldo3Text.getText(),fieldo6Text.getText(),fieldo7Text.getText(),quizNumText.getText(),
                quizWText.getText(),quizo1Text.getText(),quizo2Text.getText(),quizo3Text.getText(),quizo4Text.getText(),
                quizo5Text.getText(),quizo6Text.getText(),quiz7Text.getText(),homeworkNumText.getText(),
                homeworkWText.getText(),homeworko1Text.getText(),homeworko2Text.getText(),homeworko3Text.getText(),
                homeworko4Text.getText(),homeworko5Text.getText(),homeworko6Text.getText(),homeworko7Text.getText(),
                juryNumText.getText(),juryWText.getText(),juryo1Text.getText(),juryo2Text.getText(),juryo3Text.getText(),
                juryo4Text.getText(),juryo5Text.getText(),juryo6Text.getText(),juryo6Text.getText(),
                ProjectNumText.getText(),projectWText.getText(),projecto1Text.getText(),projecto2Text.getText(),
                projecto3Text.getText(),projecto4Text.getText(),projecto5Text.getText(),projecto6Text.getText(),
                projecto7Text.getText(),seminarNumText.getText(),seminarWText.getText(),seminaro1Text.getText(),
                seminaro2Text.getText(),seminaro3Text.getText(),seminaro4Text.getText(),seminaro5Text.getText(),
                seminaro6Text.getText(),seminaro7Text.getText(),oralNumText.getText(),oralWText.getText(),
                oralo1Text.getText(),oralo2Text.getText(),oralo3Text.getText(),oralo4Text.getText(),oralo5Text.getText(),
                oralo6Text.getText(),oralo7Text.getText(),midtermNum.getText(),
                midtermW.getText(),midtermo1Text.getText(),midtermo2Text.getText(),midtermo3Text.getText(),
                midtermo4Text.getText(),midtermo5Text.getText(),midtermo6Text.getText(),midtermo7Text.getText(),
                finalNumText.getText(),finalWText.getText(),finalo1Text.getText(),finalo2Text.getText(),
                finalo3Text.getText(),finalo4Text.getText(),finalo5Text.getText(),finalo6Text.getText(),
                finalo7Text.getText(),totalNumText.getText(),totalWWText.getText(),totalo1Text.getText(),
                totalo2Text.getText(),totalo3Text.getText(),totalo4Text.getText(),totalo5Text.getText(),
                totalo6Text.getText(),totalo7Text.getText(),finalgrade1.getText(),finalgrade2.getText(),
                finalEGrade1.getText(),finalEGrade2.getText(),totalG1.getText(),totalG2.getText()
        };
            course.setTAB4(TAB4);
            //Tab5
            course.setCourseHoursDur(cdText.getText());
            course.setCourseHoursWL(cwText.getText());
            course.setLabOrApplicationHoursDur(ldText.getText());
            course.setLabOrApplicationHourWL(lwText.getText());
            course.setStudyHourseOutsideClass(snText.getText());
            course.setStudyHourseOutsideClassDur(sdText.getText());
            course.setStudyHourseOutsideClassWL(swText.getText());
            course.setFieldWork(fnText.getText());
            course.setFieldWorkDur(fdText.getText());
            course.setFieldWorkWL(fwText.getText());
            course.setQuizOrStudioCritique(qnText1.getText());
            course.setQuizOrStudioCritiqueDur(qdText1.getText());
            course.setStudyHourseOutsideClassWL(qwText.getText());
            course.setHomeworkOrAssignment(hnText.getText());
            course.setHomeworkOrAssignmentDur(hdText.getText());
            course.setHomeworkOrAssignmentWL(hwText.getText());
            course.setPresentationOrJury(prnText.getText());
            course.setPresentationOrJuryDur(prdText.getText());
            course.setPresentationOrJuryWL(prwText.getText());
            course.setProject(pronText.getText());
            course.setProjectDur(prodText.getText());
            course.setProjectWL(prowText.getText());
            course.setPortfolio(portnText.getText());
            course.setPortfolioDur(portdText11.getText());
            course.setPortfolioWL(portwText11.getText());
            course.setSeminarOrWorkshop(semnText.getText());
            course.setSeminarOrWorkshopDur(semdText.getText());
            course.setSeminarOrWorkshopWL(semwText.getText());
            course.setOralExam(ornText.getText());
            course.setOralExamDur(ordText.getText());
            course.setOralExamWL(orwText.getText());
            course.setMidterm(midnText.getText());
            course.setMidtermDur(middText.getText());
            course.setMidtermWL(midwText.getText());
            course.setFinal(fnText.getText());
            course.setFinalDur(fdText.getText());
            course.setFinalWL(fwText.getText());
            course.setTotalWorkload(totalWWText.getText());

        String[] TAB5 = {cdText.getText(),cwText.getText(),ldText.getText(),lwText.getText(),snText.getText(),
                sdText.getText(),swText.getText(),fnText.getText(),fdText.getText(),fwText.getText(),qnText1.getText(),
                qdText1.getText(),qwText.getText(),hnText.getText(),hdText.getText(),hwText.getText(),
                prnText.getText(),prdText.getText(),prwText.getText(),pronText.getText(),prodText.getText(),
                prowText.getText(),portnText.getText(),portdText11.getText(),portwText11.getText(),semnText.getText(),
                semdText.getText(), semwText.getText(),ornText.getText(),ordText.getText(),orwText.getText(),
                midnText.getText(),middText.getText(),midwText.getText(),fnText.getText(),fdText.getText(),
                fwText.getText(), totalWWText.getText()
        };
            course.setTAB5(TAB5);
            //Tab6
            course.setProgramCompetenciesOutcome1(CoursePOMText1.getText());
            course.setProgramCompetenciesOutcome1ContributionLevel1(CoursePOMText11.getText());
            course.setProgramCompetenciesOutcome1ContributionLevel2(CoursePOMText12.getText());
            course.setProgramCompetenciesOutcome1ContributionLevel3(CoursePOMText13.getText());
            course.setProgramCompetenciesOutcome1ContributionLevel4(CoursePOMText14.getText());
            course.setProgramCompetenciesOutcome1ContributionLevel5(CoursePOMText15.getText());
            course.setProgramCompetenciesOutcome1LOno(CoursePOMText1LO.getText());
            course.setProgramCompetenciesOutcome2(CoursePOMText2.getText());
            course.setProgramCompetenciesOutcome2ContributionLevel1(CoursePOMText21.getText());
            course.setProgramCompetenciesOutcome2ContributionLevel2(CoursePOMText22.getText());
            course.setProgramCompetenciesOutcome2ContributionLevel3(CoursePOMText23.getText());
            course.setProgramCompetenciesOutcome2ContributionLevel4(CoursePOMText24.getText());
            course.setProgramCompetenciesOutcome2ContributionLevel5(CoursePOMText25.getText());
            course.setProgramCompetenciesOutcome2LOno(CoursePOMText2LO.getText());
            course.setProgramCompetenciesOutcome3(CoursePOMText3.getText());
            course.setProgramCompetenciesOutcome3ContributionLevel1(CoursePOMText31.getText());
            course.setProgramCompetenciesOutcome3ContributionLevel2(CoursePOMText32.getText());
            course.setProgramCompetenciesOutcome3ContributionLevel3(CoursePOMText33.getText());
            course.setProgramCompetenciesOutcome3ContributionLevel4(CoursePOMText34.getText());
            course.setProgramCompetenciesOutcome3ContributionLevel5(CoursePOMText35.getText());
            course.setProgramCompetenciesOutcome3LOno(CoursePOMText32LO.getText());
            course.setProgramCompetenciesOutcome4(CoursePOMText4.getText());
            course.setProgramCompetenciesOutcome4ContributionLevel1(CoursePOMText41.getText());
            course.setProgramCompetenciesOutcome4ContributionLevel2(CoursePOMText42.getText());
            course.setProgramCompetenciesOutcome4ContributionLevel3(CoursePOMText43.getText());
            course.setProgramCompetenciesOutcome4ContributionLevel4(CoursePOMText44.getText());
            course.setProgramCompetenciesOutcome4ContributionLevel5(CoursePOMText45.getText());
            course.setProgramCompetenciesOutcome4LOno(CoursePOMText4LO.getText());
            course.setProgramCompetenciesOutcome5(CoursePOMText5.getText());
            course.setProgramCompetenciesOutcome5ContributionLevel1(CoursePOMText51.getText());
            course.setProgramCompetenciesOutcome5ContributionLevel2(CoursePOMText52.getText());
            course.setProgramCompetenciesOutcome5ContributionLevel3(CoursePOMText53.getText());
            course.setProgramCompetenciesOutcome5ContributionLevel4(CoursePOMText54.getText());
            course.setProgramCompetenciesOutcome5ContributionLevel5(CoursePOMText55.getText());
            course.setProgramCompetenciesOutcome5LOno(CoursePOMText5LO.getText());
            course.setProgramCompetenciesOutcome6(CoursePOMText6.getText());
            course.setProgramCompetenciesOutcome6ContributionLevel1(CoursePOMText61.getText());
            course.setProgramCompetenciesOutcome6ContributionLevel2(CoursePOMText62.getText());
            course.setProgramCompetenciesOutcome6ContributionLevel3(CoursePOMText63.getText());
            course.setProgramCompetenciesOutcome6ContributionLevel4(CoursePOMText64.getText());
            course.setProgramCompetenciesOutcome6ContributionLevel5(CoursePOMText65.getText());
            course.setProgramCompetenciesOutcome6LOno(CoursePOMText6LO.getText());
            course.setProgramCompetenciesOutcome7(CoursePOMText7.getText());
            course.setProgramCompetenciesOutcome7ContributionLevel1(CoursePOMText71.getText());
            course.setProgramCompetenciesOutcome7ContributionLevel2(CoursePOMText72.getText());
            course.setProgramCompetenciesOutcome7ContributionLevel3(CoursePOMText73.getText());
            course.setProgramCompetenciesOutcome7ContributionLevel4(CoursePOMText74.getText());
            course.setProgramCompetenciesOutcome7ContributionLevel5(CoursePOMText75.getText());
            course.setProgramCompetenciesOutcome7LOno(CoursePOMText7LO.getText());
            course.setProgramCompetenciesOutcome8(CoursePOMText8.getText());
            course.setProgramCompetenciesOutcome8ContributionLevel1(CoursePOMText81.getText());
            course.setProgramCompetenciesOutcome8ContributionLevel2(CoursePOMText82.getText());
            course.setProgramCompetenciesOutcome8ContributionLevel3(CoursePOMText83.getText());
            course.setProgramCompetenciesOutcome8ContributionLevel4(CoursePOMText84.getText());
            course.setProgramCompetenciesOutcome8ContributionLevel5(CoursePOMText85.getText());
            course.setProgramCompetenciesOutcome8LOno(CoursePOMText8LO.getText());
            course.setProgramCompetenciesOutcome9(CoursePOMText9.getText());
            course.setProgramCompetenciesOutcome9ContributionLevel1(CoursePOMText91.getText());
            course.setProgramCompetenciesOutcome9ContributionLevel2(CoursePOMText92.getText());
            course.setProgramCompetenciesOutcome9ContributionLevel3(CoursePOMText93.getText());
            course.setProgramCompetenciesOutcome9ContributionLevel4(CoursePOMText94.getText());
            course.setProgramCompetenciesOutcome9ContributionLevel5(CoursePOMText95.getText());
            course.setProgramCompetenciesOutcome9LOno(CoursePOMText9LO.getText());
            course.setProgramCompetenciesOutcome10(CoursePOMText10.getText());
            course.setProgramCompetenciesOutcome10ContributionLevel1(CoursePOMText101.getText());
            course.setProgramCompetenciesOutcome10ContributionLevel2(CoursePOMText102.getText());
            course.setProgramCompetenciesOutcome10ContributionLevel3(CoursePOMText103.getText());
            course.setProgramCompetenciesOutcome10ContributionLevel4(CoursePOMText104.getText());
            course.setProgramCompetenciesOutcome10ContributionLevel5(CoursePOMText105.getText());
            course.setProgramCompetenciesOutcome10LOno(CoursePOMText10LO.getText());
            course.setProgramCompetenciesOutcome11(CoursePOMText110.getText());
            course.setProgramCompetenciesOutcome11ContributionLevel1(CoursePOMText111.getText());
            course.setProgramCompetenciesOutcome11ContributionLevel2(CoursePOMText112.getText());
            course.setProgramCompetenciesOutcome11ContributionLevel3(CoursePOMText113.getText());
            course.setProgramCompetenciesOutcome11ContributionLevel4(CoursePOMText114.getText());
            course.setProgramCompetenciesOutcome11ContributionLevel5(CoursePOMText115.getText());
            course.setProgramCompetenciesOutcome11LOno(CoursePOMText11LO.getText());
            course.setProgramCompetenciesOutcome12(CoursePOMText120.getText());
            course.setProgramCompetenciesOutcome12ContributionLevel1(CoursePOMText121.getText());
            course.setProgramCompetenciesOutcome12ContributionLevel2(CoursePOMText122.getText());
            course.setProgramCompetenciesOutcome12ContributionLevel3(CoursePOMText123.getText());
            course.setProgramCompetenciesOutcome12ContributionLevel4(CoursePOMText124.getText());
            course.setProgramCompetenciesOutcome12ContributionLevel5(CoursePOMText125.getText());
            course.setProgramCompetenciesOutcome12LOno(CoursePOMText12LO.getText());
            course.setProgramCompetenciesOutcome13(CoursePOMText130.getText());
            course.setProgramCompetenciesOutcome13ContributionLevel1(CoursePOMText131.getText());
            course.setProgramCompetenciesOutcome13ContributionLevel2(CoursePOMText132.getText());
            course.setProgramCompetenciesOutcome13ContributionLevel3(CoursePOMText133.getText());
            course.setProgramCompetenciesOutcome13ContributionLevel4(CoursePOMText134.getText());
            course.setProgramCompetenciesOutcome13ContributionLevel5(CoursePOMText135.getText());
            course.setProgramCompetenciesOutcome13LOno(CoursePOMText13LO.getText());
        String[] TAB6 = {
                CoursePOMText1.getText(),CoursePOMText11.getText(),CoursePOMText12.getText(),CoursePOMText13.getText(),
                CoursePOMText14.getText(),CoursePOMText15.getText(),CoursePOMText1LO.getText(),CoursePOMText2.getText(),
                CoursePOMText21.getText(),CoursePOMText22.getText(),CoursePOMText23.getText(),CoursePOMText24.getText(),
                CoursePOMText25.getText(),CoursePOMText2LO.getText(),CoursePOMText3.getText(),CoursePOMText31.getText(),
                CoursePOMText32.getText(),CoursePOMText33.getText(),CoursePOMText34.getText(),CoursePOMText35.getText(),
                CoursePOMText32LO.getText(),CoursePOMText4.getText(),CoursePOMText41.getText(),CoursePOMText42.getText(),
                CoursePOMText43.getText(),CoursePOMText44.getText(),CoursePOMText45.getText(),CoursePOMText4LO.getText(),
                CoursePOMText5.getText(),CoursePOMText51.getText(),CoursePOMText52.getText(),CoursePOMText53.getText(),
                CoursePOMText54.getText(),CoursePOMText55.getText(),CoursePOMText5LO.getText(),CoursePOMText6.getText(),
                CoursePOMText61.getText(),CoursePOMText62.getText(),CoursePOMText63.getText(),CoursePOMText64.getText(),
                CoursePOMText65.getText(),CoursePOMText6LO.getText(),CoursePOMText7.getText(),CoursePOMText71.getText(),
                CoursePOMText72.getText(),CoursePOMText73.getText(),CoursePOMText74.getText(),CoursePOMText75.getText(),
                CoursePOMText7LO.getText(),CoursePOMText8.getText(),CoursePOMText81.getText(),CoursePOMText82.getText(),
                CoursePOMText83.getText(),CoursePOMText84.getText(),CoursePOMText85.getText(),CoursePOMText8LO.getText(),
                CoursePOMText9.getText(),CoursePOMText91.getText(),CoursePOMText92.getText(),CoursePOMText93.getText(),
                CoursePOMText94.getText(),CoursePOMText95.getText(),CoursePOMText9LO.getText(),CoursePOMText10.getText(),
                CoursePOMText101.getText(),CoursePOMText102.getText(), CoursePOMText103.getText(),
                CoursePOMText104.getText(),CoursePOMText105.getText(),CoursePOMText10LO.getText(),
                CoursePOMText110.getText(),CoursePOMText111.getText(),CoursePOMText112.getText(),
                CoursePOMText113.getText(),CoursePOMText114.getText(),CoursePOMText115.getText(),
                CoursePOMText11LO.getText(),CoursePOMText120.getText(), CoursePOMText121.getText(),
                CoursePOMText122.getText(),CoursePOMText123.getText(),CoursePOMText124.getText(),
                CoursePOMText125.getText(),CoursePOMText12LO.getText(),CoursePOMText130.getText(),
                CoursePOMText131.getText(),CoursePOMText132.getText(),CoursePOMText133.getText(),
                CoursePOMText134.getText(),CoursePOMText135.getText(),CoursePOMText13LO.getText()
        };
        course.setTAB6(TAB6);
            //Tab save
            course.setReasonForUpdate(SaveAllText.getText());

        }



    @FXML
    private Label file1;
    @FXML
    private Label file2;

    @FXML
    private Label warning;


    @FXML
    private javafx.scene.control.ListView<String> SaveTab;

    @FXML
    private javafx.scene.control.ListView<String> Tab1list = new ListView<>();

    @FXML
    private javafx.scene.control.ListView<String> Tab2list = new ListView<>();

    @FXML
    private javafx.scene.control.ListView<String> Tab3list= new ListView<>();

    @FXML
    private javafx.scene.control.ListView<String> Tab4list= new ListView<>();

    @FXML
    private javafx.scene.control.ListView<String> Tab5list= new ListView<>();

    @FXML
    private javafx.scene.control.ListView<String> Tab6list= new ListView<>();

    @FXML
    private javafx.scene.control.ListView<String> ListView = new ListView<>();
    @FXML
    private ComboBox<String> comboBox = new ComboBox<>();
    static String[] Keys = new String[100];
    static String[] values = new String[100];
    static HashMap fileMap;



    @FXML
    private TextField searchText;

    @FXML
    private TextField CourseLanguageText;

    @FXML
    private TextField CourseLevelText;

    @FXML
    private TextArea CoursePOMText1;

    @FXML
    private TextArea CoursePOMText10;

    @FXML
    private TextField CoursePOMText101;

    @FXML
    private TextField CoursePOMText102;

    @FXML
    private TextField CoursePOMText103;

    @FXML
    private TextField CoursePOMText104;

    @FXML
    private TextField CoursePOMText105;

    @FXML
    private TextField CoursePOMText10LO;

    @FXML
    private TextField CoursePOMText11;

    @FXML
    private TextArea CoursePOMText110;

    @FXML
    private TextField CoursePOMText111;

    @FXML
    private TextField CoursePOMText112;

    @FXML
    private TextField CoursePOMText113;

    @FXML
    private TextField CoursePOMText114;

    @FXML
    private TextField CoursePOMText115;

    @FXML
    private TextField CoursePOMText11LO;

    @FXML
    private TextField CoursePOMText12;

    @FXML
    private TextArea CoursePOMText120;

    @FXML
    private TextField CoursePOMText121;

    @FXML
    private TextField CoursePOMText122;

    @FXML
    private TextField CoursePOMText123;

    @FXML
    private TextField CoursePOMText124;

    @FXML
    private TextField CoursePOMText125;

    @FXML
    private TextField CoursePOMText12LO;

    @FXML
    private TextField CoursePOMText13;

    @FXML
    private TextArea CoursePOMText130;

    @FXML
    private TextField CoursePOMText131;

    @FXML
    private TextField CoursePOMText132;

    @FXML
    private TextField CoursePOMText133;

    @FXML
    private TextField CoursePOMText134;

    @FXML
    private TextField CoursePOMText135;

    @FXML
    private TextField CoursePOMText13LO;

    @FXML
    private TextField CoursePOMText14;

    @FXML
    private TextField CoursePOMText15;

    @FXML
    private TextField CoursePOMText1LO;

    @FXML
    private TextArea CoursePOMText2;

    @FXML
    private TextField CoursePOMText21;

    @FXML
    private TextField CoursePOMText22;

    @FXML
    private TextField CoursePOMText23;

    @FXML
    private TextField CoursePOMText24;

    @FXML
    private TextField CoursePOMText25;

    @FXML
    private TextField CoursePOMText2LO;

    @FXML
    private TextArea CoursePOMText3;

    @FXML
    private TextField CoursePOMText31;

    @FXML
    private TextField CoursePOMText32;

    @FXML
    private TextField CoursePOMText32LO;

    @FXML
    private TextField CoursePOMText33;

    @FXML
    private TextField CoursePOMText34;

    @FXML
    private TextField CoursePOMText35;

    @FXML
    private TextArea CoursePOMText4;

    @FXML
    private TextField CoursePOMText41;

    @FXML
    private TextField CoursePOMText42;

    @FXML
    private TextField CoursePOMText43;

    @FXML
    private TextField CoursePOMText44;

    @FXML
    private TextField CoursePOMText45;

    @FXML
    private TextField CoursePOMText4LO;

    @FXML
    private TextArea CoursePOMText5;

    @FXML
    private TextField CoursePOMText51;

    @FXML
    private TextField CoursePOMText52;

    @FXML
    private TextField CoursePOMText53;

    @FXML
    private TextField CoursePOMText54;

    @FXML
    private TextField CoursePOMText55;

    @FXML
    private TextField CoursePOMText5LO;

    @FXML
    private TextArea CoursePOMText6;

    @FXML
    private TextField CoursePOMText61;

    @FXML
    private TextField CoursePOMText62;

    @FXML
    private TextField CoursePOMText63;

    @FXML
    private TextField CoursePOMText64;

    @FXML
    private TextField CoursePOMText65;

    @FXML
    private TextField CoursePOMText6LO;

    @FXML
    private TextArea CoursePOMText7;

    @FXML
    private TextField CoursePOMText71;

    @FXML
    private TextField CoursePOMText72;

    @FXML
    private TextField CoursePOMText73;

    @FXML
    private TextField CoursePOMText74;

    @FXML
    private TextField CoursePOMText75;

    @FXML
    private TextField CoursePOMText7LO;

    @FXML
    private TextArea CoursePOMText8;

    @FXML
    private TextField CoursePOMText81;

    @FXML
    private TextField CoursePOMText82;

    @FXML
    private TextField CoursePOMText83;

    @FXML
    private TextField CoursePOMText84;

    @FXML
    private TextField CoursePOMText85;

    @FXML
    private TextField CoursePOMText8LO;

    @FXML
    private TextArea CoursePOMText9;

    @FXML
    private TextField CoursePOMText91;

    @FXML
    private TextField CoursePOMText92;

    @FXML
    private TextField CoursePOMText93;

    @FXML
    private TextField CoursePOMText94;

    @FXML
    private TextField CoursePOMText95;

    @FXML
    private TextField CoursePOMText9LO;

    @FXML
    private TextField CourseTypeText;


    @FXML
    private TextField ModeOfDeleveryText;


    @FXML
    private TextArea ProjectNumText;

    @FXML
    private TextArea SaveAllText;


    @FXML
    private TextField applicationText2;


    @FXML
    private TextField assistantText;

    @FXML
    private TextField cdText;


    @FXML
    private TextField codeTextt2;


    @FXML
    private TextField communicationandManagementSkillsCourseText;


    @FXML
    private TextField coreCourseText;


    @FXML
    private TextField courseNameText2;


    @FXML
    private TextArea courseNotesText;


    @FXML
    private TextField coursecoordinatorText;


    @FXML
    private TextArea coursedescriptionText;


    @FXML
    private TextField courselecturerText;


    @FXML
    private TextArea courseobjectivesText;

    @FXML
    private TextField cwText;


    @FXML
    private TextField ectsText2;


    @FXML
    private Button engButton;


    @FXML
    private TextField exdText;

    @FXML
    private TextField exnText;

    @FXML
    private TextField exwText;

    @FXML
    private TextField fdText;


    @FXML
    private TextArea fieldNumText;


    @FXML
    private TextArea fieldWText;

    @FXML
    private TextArea fieldo1Text;

    @FXML
    private TextArea fieldo2Text;

    @FXML
    private TextArea fieldo3Text;

    @FXML
    private TextArea fieldo4Text;

    @FXML
    private TextArea fieldo5Text;

    @FXML
    private TextArea fieldo6Text;

    @FXML
    private TextArea fieldo7Text;

    @FXML
    private TextField finalEGrade1;

    @FXML
    private TextField finalEGrade2;


    @FXML
    private TextArea finalNumText;

    @FXML
    private TextArea finalWText;

    @FXML
    private TextField finalgrade1;

    @FXML
    private TextField finalgrade2;


    @FXML
    private TextArea finalo1Text;

    @FXML
    private TextArea finalo2Text;

    @FXML
    private TextArea finalo3Text;

    @FXML
    private TextArea finalo4Text;

    @FXML
    private TextArea finalo5Text;

    @FXML
    private TextArea finalo6Text;

    @FXML
    private TextArea finalo7Text;

    @FXML
    private TextField fnText;

    @FXML
    private TextField fwText;


    @FXML
    private TextField hdText;

    @FXML
    private TextField hnText;


    @FXML
    private TextArea homeworkNumText;


    @FXML
    private TextArea homeworkWText;

    @FXML
    private TextArea homeworko1Text;

    @FXML
    private TextArea homeworko2Text;

    @FXML
    private TextArea homeworko3Text;

    @FXML
    private TextArea homeworko4Text;

    @FXML
    private TextArea homeworko5Text;

    @FXML
    private TextArea homeworko6Text;

    @FXML
    private TextArea homeworko7Text;


    @FXML
    private TextField hwText;


    @FXML
    private TextArea juryNumText;

    @FXML
    private TextArea juryWText;

    @FXML
    private TextArea juryo1Text;

    @FXML
    private TextArea juryo2Text;

    @FXML
    private TextArea juryo3Text;

    @FXML
    private TextArea juryo4Text;

    @FXML
    private TextArea juryo5Text;

    @FXML
    private TextArea juryo6Text;

    @FXML
    private TextArea juryo7Text;


    @FXML
    private TextArea labNumText;


    @FXML
    private TextArea labWText;

    @FXML
    private TextArea labo1Text;

    @FXML
    private TextArea labo2Text;

    @FXML
    private TextArea labo3Text;

    @FXML
    private TextArea labo4Text;

    @FXML
    private TextArea labo5Text;

    @FXML
    private TextArea labo6Text;

    @FXML
    private TextArea labo7Text;

    @FXML
    private TextField ldText;


    @FXML
    private TextArea learningoutcomesText;


    @FXML
    private TextField localCreditsText2;

    @FXML
    private TextField lwText;


    @FXML
    private TextField majorAreaCourseText;


    @FXML
    private TextField middText;

    @FXML
    private TextField midnText;


    @FXML
    private TextArea midtermNum;

    @FXML
    private TextArea midtermW;

    @FXML
    private TextArea midtermo1Text;

    @FXML
    private TextArea midtermo2Text;

    @FXML
    private TextArea midtermo3Text;

    @FXML
    private TextArea midtermo4Text;

    @FXML
    private TextArea midtermo5Text;

    @FXML
    private TextArea midtermo6Text;

    @FXML
    private TextArea midtermo7Text;

    @FXML
    private TextField midwText;


    @FXML
    private TextArea oralNumText;


    @FXML
    private TextArea oralWText;

    @FXML
    private TextArea oralo1Text;

    @FXML
    private TextArea oralo2Text;

    @FXML
    private TextArea oralo3Text;

    @FXML
    private TextArea oralo4Text;

    @FXML
    private TextArea oralo5Text;

    @FXML
    private TextArea oralo6Text;

    @FXML
    private TextArea oralo7Text;

    @FXML
    private TextField ordText;

    @FXML
    private TextField ornText;

    @FXML
    private TextField orwText;


    @FXML
    private TextArea participateNumText;

    @FXML
    private TextArea participateWText;

    @FXML
    private TextArea participateo1Text;

    @FXML
    private TextArea participateo2Text;

    @FXML
    private TextArea participateo3Text;

    @FXML
    private TextArea participateo4Text;

    @FXML
    private TextArea participateo5Text;

    @FXML
    private TextArea participateo6Text;

    @FXML
    private TextArea participateo7Text;


    @FXML
    private TextField portdText11;

    @FXML
    private TextField portnText;

    @FXML
    private TextField portwText11;

    @FXML
    private TextField prdText;


    @FXML
    private TextField prerequisitesText;


    @FXML
    private TextField prnText;

    @FXML
    private TextField prodText;


    @FXML
    private TextArea projectWText;

    @FXML
    private TextArea projecto1Text;

    @FXML
    private TextArea projecto2Text;

    @FXML
    private TextArea projecto3Text;

    @FXML
    private TextArea projecto4Text;

    @FXML
    private TextArea projecto5Text;

    @FXML
    private TextArea projecto6Text;

    @FXML
    private TextArea projecto7Text;

    @FXML
    private TextField pronText;

    @FXML
    private TextField prowText;

    @FXML
    private TextField prwText;

    @FXML
    private TextField qdText1;

    @FXML
    private TextField qnText1;

    @FXML
    private TextArea quiz7Text;


    @FXML
    private TextArea quizNumText;


    @FXML
    private TextArea quizWText;

    @FXML
    private TextArea quizo1Text;

    @FXML
    private TextArea quizo2Text;

    @FXML
    private TextArea quizo3Text;

    @FXML
    private TextArea quizo4Text;

    @FXML
    private TextArea quizo5Text;

    @FXML
    private TextArea quizo6Text;

    @FXML
    private TextField qwText;

    @FXML
    private TextArea reqMaterial1;

    @FXML
    private TextArea reqMaterial101;

    @FXML
    private TextArea reqMaterial11;

    @FXML
    private TextArea reqMaterial111;

    @FXML
    private TextArea reqMaterial121;

    @FXML
    private TextArea reqMaterial131;

    @FXML
    private TextArea reqMaterial141;

    @FXML
    private TextArea reqMaterial21;

    @FXML
    private TextArea reqMaterial31;

    @FXML
    private TextArea reqMaterial41;

    @FXML
    private TextArea reqMaterial51;

    @FXML
    private TextArea reqMaterial61;

    @FXML
    private TextArea reqMaterial71;

    @FXML
    private TextArea reqMaterial81;

    @FXML
    private TextArea reqMaterial91;

    @FXML
    private TextField sdText;


    @FXML
    private TextField seasontext;

    @FXML
    private TextField semdText;


    @FXML
    private TextArea seminarNumText;


    @FXML
    private TextArea seminarWText;

    @FXML
    private TextArea seminaro1Text;

    @FXML
    private TextArea seminaro2Text;

    @FXML
    private TextArea seminaro3Text;

    @FXML
    private TextArea seminaro4Text;

    @FXML
    private TextArea seminaro5Text;

    @FXML
    private TextArea seminaro6Text;

    @FXML
    private TextArea seminaro7Text;

    @FXML
    private TextField semnText;

    @FXML
    private TextField semwText;

    @FXML
    private TextField snText;


    @FXML
    private TextArea subject1;

    @FXML
    private TextArea subject101;

    @FXML
    private TextArea subject11;

    @FXML
    private TextArea subject111;

    @FXML
    private TextArea subject121;

    @FXML
    private TextArea subject131;

    @FXML
    private TextArea subject141;

    @FXML
    private TextArea subject21;

    @FXML
    private TextArea subject31;

    @FXML
    private TextArea subject41;

    @FXML
    private TextArea subject51;

    @FXML
    private TextArea subject61;

    @FXML
    private TextArea subject71;

    @FXML
    private TextArea subject81;

    @FXML
    private TextArea subject91;


    @FXML
    private TextArea suggestedReadingsText;


    @FXML
    private TextField supportiveCourseText;

    @FXML
    private TextField swText;


    @FXML
    private TextArea teachingMethodsText;


    @FXML
    private TextField theoryText2;

    @FXML
    private TextField totalG1;

    @FXML
    private TextField totalG2;


    @FXML
    private TextArea totalNumText;


    @FXML
    private TextArea totalWWText;

    @FXML
    private TextField totallWText;

    @FXML
    private TextArea totalo1Text;

    @FXML
    private TextArea totalo2Text;

    @FXML
    private TextArea totalo3Text;

    @FXML
    private TextArea totalo4Text;

    @FXML
    private TextArea totalo5Text;

    @FXML
    private TextArea totalo6Text;

    @FXML
    private TextArea totalo7Text;

    @FXML
    private Button trButton;


    @FXML
    private TextField transferableSkillCourseText;
        }


