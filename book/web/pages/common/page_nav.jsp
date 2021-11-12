<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:choose>
        <%--情况1：如果总页码小于等于5--%>
        <c:when test="${ requestScope.page.pageTotal <=5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--情况2：总页码大于5的情况--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情况1：当前页码为前面2个--%>
                <c:when test="${requestScope.page.pageNo < 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--小情况2：当前页码为最后2个--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-2}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--小情况3：其他情况--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <%--大于首页，才显示--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${ i == requestScope.page.pageNo }">
            【${i}】
        </c:if>
        <c:if test="${ i != requestScope.page.pageNo }">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--如果已经是最后一页，则不显示下一页，末页--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function(){
            // 跳到指定页码
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                <%--var pageTotal = ${requestScope.page.pageTotal};--%>
                // alert(pageTotal)
                // js语言中提供了一个 location 地址栏对象
                // 它有一个属性叫 href. 它可以获取浏览器中地址栏中的地址
                // href 属性可读可写
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo="
                    + pageNo;
            });
        });
    </script>
</div>