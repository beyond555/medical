

import read as Read
import numpy as np
import scipy as sp  
import dealPaper as d
import resume as r
import Hindex as H
import sys
import os

os.chdir("python/")
AuthorMapFile='.././info/AuthorMap'
titleMapFile='.././info/titleMap'
relationFile='.././info/paper2author'
paper2journal='.././info/paper2journal'
agencyFile='.././info/AuthorAgency'
citefile='../'
IF_file='./'
citefile+=sys.argv[1]
IF_file+=sys.argv[3]
Type=sys.argv[4]
for i in range(1,2):
	t=str(i)
	ansFile='./ans/'+sys.argv[2]
	(AuthorMap,PaperMap,Relation,classPaperMap,agencyMap,IF,pj)=Read.run(AuthorMapFile,titleMapFile,relationFile,citefile,agencyFile,IF_file,paper2journal)
	d.deal(AuthorMap, PaperMap, Relation, classPaperMap,agencyMap,citefile,ansFile,IF,pj,Type)
	
#	H.Hindex('./h_index/',AuthorMap,agencyMap)
#	



