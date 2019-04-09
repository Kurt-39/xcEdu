<!DOCTYPE html>
<html>
<head>
        <meta charset="utf‐8">
        <title>Hello World!</title>
</head>
<body>
<table>
    <tr>
             <td>序号</td>    
                <td>姓名</td>
                <td>年龄</td>
                <td>钱包</td>
            </tr>

    <#list stus as stu>
        <tr>
            <td>${stu_index+1}</td>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.mondy}</td>

        </tr>
    </#list>

    输出stu1的学生信息：
    ${stuMap['stu1'].name}
    ${stuMap['stu1'].age}<br>
    输出stu2的学生信息：
    ${stuMap.stu2.name}
    ${stuMap.stu2.age}
    <#list stuMap?keys as k>
        <tr>
            <td>${k_index+2}</td>
            <td>${stuMap[k].name}</td>
            <td>${stuMap[k].age}</td>
            <td>${stuMap[k].mondy}</td>
        </tr>

    </#list>
</table>
</body>
</html>