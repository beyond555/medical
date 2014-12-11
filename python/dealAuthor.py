import sys
import csv
import numpy as np
import os
from numpy import *



def deal(AuthorMap,PaperMap,Relation,classPaperMap,agencyMap,filename,ansFile):
	(matrix,dict,mapList,authorCnt)=run(AuthorMap,PaperMap,Relation,classPaperMap,filename)
	sortList=pagerank(matrix,mapList)
	showAns(sortList, dict,AuthorMap,agencyMap,ansFile,authorCnt)

def pagerank(H,mapList):
    n = len(H)
    w = zeros(n)
    rho = 1./n * ones(n);
    for i in range(n):
      if multiply.reduce(H[i]== zeros(n)):
        w[i] = 1
    newH = H + outer((1./n * w),ones(n))

    theta=0.85
    G = (theta * newH) + ((1-theta) * outer(1./n * ones(n), ones(n)))
    for j in range(50):
        rho = dot(rho,G)
    List=[];
    for j in range(len(rho)):

    	t=(mapList[j],rho[j])
    	List.append(t)
    List.sort(lambda x,y:cmp(y[1],x[1])) 
  #  print List
    return List

def showAns(List,dict,AuthorMap,agencyMap,ansFile,authorCnt):
	print '------------------showAns-----------------'
#	print dict
#	print List
	saveout = sys.stdout  
	print os.getcwd()
	fsock = open(ansFile, 'w') 
	sys.stdout = fsock  
	
	for i in range(len(List)):
		id=List[i][0]
		name=AuthorMap.get(str(id))
		agencyMap.get(name)
		if -1!=agencyMap.get(name).find("China"):
			print name+"\t"+str(authorCnt.get(id))+"\t"+agencyMap.get(name)
	fsock.close()
	sys.stdout = saveout


def run(AuthorMap,PaperMap,Relation,classPaperMap,filename):
	csvfile = file(filename, 'rb')
	reader = csv.reader(csvfile)
	dict={};
	authorCnt={};
	List=[]
	num=0
	print '------------------------------'
	for line in reader:
		str=''.join(line)
		ans=str.split('\t')
		if(len(ans)<1):
			break
		for j in range(0,len(ans)):
			if PaperMap.has_key(ans[j]) and classPaperMap.has_key(ans[j]):
				pid=PaperMap.get(ans[j])
				authorlist=Relation[int(pid)]
				for k in range(0,len(authorlist)):
					author=authorlist[k]
					if not dict.has_key(author):
						dict[author]=num
						List.append([]);
						List[num]=author
						num+=1
	matrix = setMatrix(num)
#	print authorCnt
#	print num
#	print dict
#	print List
	csvfile = file(filename, 'rb')
	reader = csv.reader(csvfile)
	for line in reader:
		str=''.join(line)
		ans=str.split('\t')
		if(len(ans)<1):
			break
		if PaperMap.has_key(ans[0]) and classPaperMap.has_key(ans[0]):
			p1=PaperMap.get(ans[0])
			plist1=Relation[int(p1)]
			for k in range(0,len(plist1)):
				author=plist1[k]
				if not authorCnt.has_key(author):
					authorCnt[author]=1
				else:
					authorCnt[author]=authorCnt[author]+1;
			for j in range(1,len(ans)):
				if PaperMap.has_key(ans[j]) and classPaperMap.has_key(ans[j]):
					pid=PaperMap.get(ans[j])
					if not p1 == pid:
						plist2=Relation[int(pid)]
						count(matrix,plist1,plist2,dict,List)
	csvfile.close()
#	print matrix
	for i in range(0,num):
		t =  sum([item for item in matrix[i]])
		if t > 0:
			for j in range(0,num):
				matrix[i][j]=float(matrix[i][j])/t
#	print matrix
	return (matrix,dict,List,authorCnt)
#i j or j i??????
def count(matrix,plist1,plist2,dict,List):
	
	
	for i in range(0,len(plist1)):
		id1=plist1[i]
		for j in range(0,len(plist2)):
			id2=plist2[j]
			matrix[ dict[id2]] [ dict[id1] ] += 1
		#	print List[dict[id1]]+' '+List[dict[id2]]
def setMatrix(num):
	return [ [0]*num for i in range(num)]


