<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>

    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body">
                    <!-- Title -->
                    <fo:block font-size="20pt" font-family="Helvetica" font-weight="bold" text-align="center" space-after="20mm">
                        Student Information Report
                    </fo:block>

                    <!-- Student Details -->
                    <fo:block font-size="12pt" font-family="Helvetica" space-after="5mm">
                        <fo:inline font-weight="bold">Name: </fo:inline> <xsl:value-of select="student/name"/>
                    </fo:block>
                    <fo:block font-size="12pt" font-family="Helvetica" space-after="5mm">
                        <fo:inline font-weight="bold">Age: </fo:inline> <xsl:value-of select="student/age"/>
                    </fo:block>
                    <fo:block font-size="12pt" font-family="Helvetica" space-after="5mm">
                        <fo:inline font-weight="bold">GPA: </fo:inline> <xsl:value-of select="student/gpa"/>
                    </fo:block>
                    <fo:block font-size="12pt" font-family="Helvetica" space-after="5mm">
                        <fo:inline font-weight="bold">Active Status: </fo:inline>
                        <xsl:choose>
                            <xsl:when test="student/active = 'true'">Active</xsl:when>
                            <xsl:otherwise>Inactive</xsl:otherwise>
                        </xsl:choose>
                    </fo:block>
                    <fo:block font-size="12pt" font-family="Helvetica" space-after="10mm">
                        <fo:inline font-weight="bold">Grade: </fo:inline> <xsl:value-of select="student/grade"/>
                    </fo:block>

                    <!-- Subjects Table Title -->
                    <fo:block font-size="16pt" font-family="Helvetica" font-weight="bold" space-after="10mm">
                        Enrolled Subjects
                    </fo:block>

                    <!-- Subjects Table -->
                    <fo:table table-layout="fixed" width="100%" border-collapse="separate">
                        <fo:table-column column-width="10cm"/>
                        <fo:table-column column-width="7cm"/>
                        
                        <fo:table-header>
                            <fo:table-row background-color="#f2f2f2">
                                <fo:table-cell border="1pt solid black" padding="2mm">
                                    <fo:block font-weight="bold">Subject Name</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="1pt solid black" padding="2mm">
                                    <fo:block font-weight="bold">Credits</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        
                        <fo:table-body>
                            <xsl:for-each select="student/subjects/subject">
                                <fo:table-row>
                                    <fo:table-cell border="1pt solid black" padding="2mm">
                                        <fo:block><xsl:value-of select="name"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border="1pt solid black" padding="2mm">
                                        <fo:block><xsl:value-of select="credits"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                    
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>
