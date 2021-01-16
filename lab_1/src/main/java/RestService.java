import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestService extends HttpServlet {
    //  Учет медицинских лабораторных показателей пациента поликлиники
    //  (изменение с течением времени гемоглобина в крови, сахара, АЛТ, АСТ и тому подобных параметров)
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    String pagePart1 = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>!DOCTYPE</title>
                <meta charset="utf-8">
            </head>
            <body>
                   
            <h2>Учет медицинских лабораторных показателей сдачи крови пациента поликлиники</h2>

            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Пациент</th>
                    <th>Дата</th>
                    <th>Гемоглобин</th>
                    <th>Сахар</th>
                    <th>АЛТ</th>
                    <th>АСТ</th>
                </tr>
            """;

    String pagePart2 = """
            </table>

            <h3>Добавить запись</h3>

            <form action='/' method='POST'>
            <table border="0">
                <tr>
                    <td>Пациент</td>
                    <td><input name='patient' pattern="([A-Za-zА-Яа-яЁё]{3,20}\\s){2}[A-Za-zА-Яа-яЁё]{3,20}"></td>
                </tr>
                <tr>
                    <td>Гемоглобин</td>
                    <td><input name='hemoglobin' pattern="\\d{1,3}"</td>
                </tr>
                <tr>
                    <td>Сахар</td>
                    <td><input name='glucose' pattern="\\d{1,3}(\\.\\d{1})?"></td>
                </tr>
                <tr>
                    <td>АЛТ</td>
                    <td><input name='alt' pattern="\\d{1,3}"</td>
                </tr>
                <tr>
                    <td>АСТ</td>
                    <td><input name='ast' pattern="\\d{1,3}"></td>
                   <td> <button type='submit'>Добавить</button></td>
                </tr>
            </table>
            </form>

            <h3>Поиск записей</h3>
            <form method='GET'>
            <table border="0">
                <tr>
                    <td>ID</td>
                    <td><input name='id' type="number" min="0" step="1" max="2147483647"></td>
                </tr>
                <tr>
                    <td>Пациент</td>
                    <td><input name='patient'></td>
                </tr>
                <tr>
                    <td>Дата</td>
                    <td><input input type="date" name='date'></td>
                    <td> <button type='submit'>Поиск</button></td>
                </tr>
            </table>
            </form>
                       
            <h3>Удалить запись</h3>

            <form action='/' method='POST'> 
            <table border="0">
                <tr>
                    <td>ID</td>
                    <td><input name='id' type="number" min="0" step="1" max="2147483647"><br></td>
                    <input type="hidden" name="action" value="delete"/>
                    <td> <button type='submit'>Удалить</button></td>
                </tr>
            </table>
            </form>
            </body>
            </html>""";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        String patient = request.getParameter("patient");
        String date = request.getParameter("date");

        List<LabTestResult> results = Information.RESULTS.stream()
                .filter(result -> id == null || id.equals("")
                        || (id.matches("^\\d+$") && result.getId() == Integer.parseInt(id)))
                .filter(result -> patient == null || patient.equals("") || result.getPatient().contains(patient))
                .filter(result -> date == null || date.equals("") || DATE_FORMAT.format(result.getDate()).equals(date))
                .collect(Collectors.toList());


        StringBuilder table = new StringBuilder();

        for (LabTestResult result : results) {
            table.append("<tr>")
                    .append("<td>").append(result.getId()).append("</td>")
                    .append("<td>").append(result.getPatient()).append("</td>")
                    .append("<td>").append(DATE_FORMAT.format(result.getDate())).append("</td>")
                    .append("<td>").append(result.getHemoglobin()).append("</td>")
                    .append("<td>").append(result.getGlucose()).append("</td>")
                    .append("<td>").append(result.getAlt()).append("</td>")
                    .append("<td>").append(result.getAst()).append("</td>")
                    .append("</tr>");
        }


        response.getWriter().println(pagePart1 + table + pagePart2);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if ("delete".equals(request.getParameter("action"))) {
            //https://stackoverrun.com/ru/q/11100437
            doDelete(request, response);
            response.sendRedirect("");
            return;
        }

        String patient = request.getParameter("patient");
        String hemoglobin = request.getParameter("hemoglobin");
        String glucose = request.getParameter("glucose");
        String alt = request.getParameter("alt");
        String ast = request.getParameter("ast");

        // данные всегда корректны
        Information.RESULTS.add(Information.buildLabTestResult(patient, Integer.parseInt(hemoglobin),
                Float.parseFloat(glucose), Integer.parseInt(alt), Integer.parseInt(ast)));
        response.sendRedirect("/");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        if (id != null && id.matches("^\\d+$")) {
            LabTestResult found = null;

            for (LabTestResult result : Information.RESULTS) {
                if (result.getId() == Integer.parseInt(id)) {
                    found = result;
                    break;
                }
            }
            if (found != null) {
                Information.RESULTS.remove(found);
            }
        }
    }
}