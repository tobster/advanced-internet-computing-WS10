<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : OrderItemTransformation.xsl
    Created on : 11. Jänner 2011, 16:22
    Author     : Christoph Derndorfer
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:for-each select="*">
            <items>
                <quantity>
                    <xsl:value-of select="amount"></xsl:value-of>
                </quantity>
                <product>
                    <xsl:attribute name="id">
                        <xsl:value-of select="product_id"></xsl:value-of>
                    </xsl:attribute>
                </product>
            </items>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>
