import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Information {
    /*
             id                                 130—160 г/л     3.3-5.5 ммоль/л 	ЕД/л	ЕД/л
            1 Бурьян Владислав Владимирович    	131	    3.6		33	35
            2 Васильев Александр Сергеевич		142		3.4		32	35
            3 Водоватов Денис Александрович		133		3.6		33	34
            4 Голденберг Рихард Борисович		137		3.5		34	34
            5 Иванов Владимир Михайлович		139		3.3		34	33
            6 Колесникова Полина Андреевна		137		3.4		33	30
            7 Кубасов Ярослав Валерьевич		134		3.5		32	30
            8 Кузьмин Алексей Юрьевич 		    143		3.6		33	31
            9 Куликов Никита Александрович		144		3.7		33	32
            10 Маркин Павел Андреевич		    151		3.8		35	33
     */

    public final static List<LabTestResult> RESULTS = new ArrayList<>();
    private static Integer id = 1;

    static {
        RESULTS.add(buildLabTestResult("Бурьян Владислав Владимирович", 131, 3.6f, 33, 35));
        RESULTS.add(buildLabTestResult("Васильев Александр Сергеевич", 142, 3.4f, 32, 35));
        RESULTS.add(buildLabTestResult("Водоватов Денис Александрович", 133, 3.6f, 33, 34));
        RESULTS.add(buildLabTestResult("Голденберг Рихард Борисович", 137, 3.5f, 34, 34));
        RESULTS.add(buildLabTestResult("Иванов Владимир Михайлович", 139, 3.3f, 34, 33));
        RESULTS.add(buildLabTestResult("Колесникова Полина Андреевна", 137, 3.4f, 33, 30));
        RESULTS.add(buildLabTestResult("Кубасов Ярослав Валерьевич", 134, 3.5f, 32, 30));
        RESULTS.add(buildLabTestResult("Кузьмин Алексей Юрьевич", 143, 3.6f, 33, 31));
        RESULTS.add(buildLabTestResult("Куликов Никита Александрович", 144, 3.7f, 33, 32));
        RESULTS.add(buildLabTestResult("Маркин Павел Андреевич", 151, 3.8f, 35, 33));
    }

    public static LabTestResult buildLabTestResult(String patient, int hemoglobin,
                                            float glucose, int alt, int ast) {
        LabTestResult result = new LabTestResult();
        result.setId(id);
        id++;
        result.setPatient(patient);
        result.setDate(new Date());
        result.setHemoglobin(hemoglobin);
        result.setGlucose(glucose);
        result.setAlt(alt);
        result.setAst(ast);
        return result;
    }
}
