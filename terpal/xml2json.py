# -*- coding: utf-8 -*-
#!/usr/bin/env python

"""
Created on Mon Jan 19 22:33:15 2015

@author: Nikos Vasileiadis
"""

from xmlutils.xml2json import xml2json

inputs = raw_input(".xml file: ")
output = raw_input("new .json file: ")

converter = xml2json(inputs, output, encoding="utf-8")
converter.convert()
