<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes" />

    <xsl:template match="/">
        <html>
            <head>
                <title>Student Information</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    h1 { color: #2c3e50; }
                    table { border-collapse: collapse; width: 100%; margin-top: 20px; }
                    th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }
                    th { background-color: #f2f2f2; color: #333; }
                    .info { margin-bottom: 20px; }
                    .info p { margin: 5px 0; }
                </style>
            </head>
            <body>
                <h1>Student Information</h1>
                <div class="info">
                    <p><strong>Name:</strong> <xsl:value-of select="student/name"/></p>
                    <p><strong>Age:</strong> <xsl:value-of select="student/age"/></p>
                    <p><strong>GPA:</strong> <xsl:value-of select="student/gpa"/></p>
                    <p><strong>Active Status:</strong> 
                        <xsl:choose>
                            <xsl:when test="student/active = 'true'">Active</xsl:when>
                            <xsl:otherwise>Inactive</xsl:otherwise>
                        </xsl:choose>
                    </p>
                    <p><strong>Grade:</strong> <xsl:value-of select="student/grade"/></p>
                </div>

                <h2>Enrolled Subjects</h2>
                <table>
                    <tr>
                        <th>Subject Name</th>
                        <th>Credits</th>
                    </tr>
                    <xsl:for-each select="student/subjects/subject">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="credits"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
