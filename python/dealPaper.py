import sys
import csv
import numpy as np
import os
from numpy import *



def deal(AuthorMap,PaperMap,Relation,classPaperMap,agencyMap,filename,ansFile,IF,pj,Type):
	(matrix,dict,mapList,PaperCiteCnt)=run(AuthorMap,PaperMap,Relation,classPaperMap,filename,pj,IF)
	print '-----step 2------'
	sortList=pagerank(matrix,mapList,IF,pj)
	AuthorAns=PaperFactor(matrix,sortList, dict,AuthorMap,PaperMap,agencyMap,ansFile,PaperCiteCnt,mapList,Relation)
	AuthorRank(AuthorAns,ansFile,AuthorMap,agencyMap,PaperMap,Type);

def pagerank(H,mapList,IF,pj):
	n = len(H)
	w = zeros(n)
	rho = 1./n * ones(n);
	for i in range(n):
		if multiply.reduce(H[i]== zeros(n)):
			w[i] = 1
	newH = H + outer((1./n * w),ones(n))
	theta=0.85
	G = (theta * newH)+(1-theta)*outer(1./n * ones(n), ones(n))
	for j in range(50):
		print j
		rho = dot(rho,G)
	List=[];
	for j in range(len(rho)):
		t=(mapList[j],rho[j])
		List.append(t)
	List.sort(lambda x,y:cmp(y[1],x[1])) 
	#print List
	return List

def PaperFactor(matrix,List,dict,AuthorMap,PaperMap,agencyMap,ansFile,PaperCiteCnt,mapList,Relation):
	num=len(AuthorMap)
	AuthorFactor= (num+1) *[0]
	saveout = sys.stdout  
	fsock = open(ansFile+"_paper", 'w') 
	sys.stdout = fsock
	
	for i in range(len(List)):
		name=List[i][0]
		factor=List[i][1]
		print name+"\t"+str(factor)
		id=PaperMap.get(name)
		plist=Relation[int(id)]
		for j in range(len(plist)):
			AuthorFactor[int(plist[j])]+=float(factor)/len(plist)
	fsock.close()
	sys.stdout = saveout
	#print AuthorFactor
	AuthorAns=[];
	t=[];
	for j in range(1,num+1):
		t=(j,AuthorFactor[j])
		AuthorAns.append(t)
	AuthorAns.sort(lambda x,y:cmp(y[1],x[1])) 
  #  print List
	return AuthorAns
def AuthorRank(List,ansFile,AuthorMap,agencyMap,PaperMap,Type):
	saveout = sys.stdout  
	fsock = open(ansFile, 'w') 
	sys.stdout = fsock  
	for i in range(len(List)):
		id=List[i][0]
		name=AuthorMap.get(str(id))
		#when English ,need to judge
		if Type==1 and -1!=agencyMap.get(name).find("China"):
		#	print name+"\t"+str(List[i][1]*len(PaperMap))+"\t"+agencyMap.get(name)
			print name+"\t"+str(List[i][1])+"\t"+agencyMap.get(name)
		else:
		#	print name+"\t"+str(List[i][1]*len(PaperMap))+"\t"+agencyMap.get(name)
			print name+"\t"+str(List[i][1])+"\t"+agencyMap.get(name)
	fsock.close()
	sys.stdout = saveout

def run(AuthorMap,PaperMap,Relation,classPaperMap,filename,pj,IF):
	csvfile = file(filename, 'rb')
	reader = csv.reader(csvfile)
	dict={};
	PaperCiteCnt={};
	List=[]
	num=0
	for line in reader:
		string=''.join(line)
		ans=string.split('\t')
		if(len(ans)<1):
			break
		for j in range(0,len(ans)):
			if PaperMap.has_key(ans[j]) and classPaperMap.has_key(ans[j]):
				pid=PaperMap.get(ans[j])
				if not dict.has_key(pid):
					dict[pid]=num
					List.append([]);
					List[num]=ans[j]
					num+=1
	matrix = setMatrix(num)
	csvfile = file(filename, 'rb')
	reader = csv.reader(csvfile)
	for line in reader:
		string=''.join(line)
		ans=string.split('\t')
		if(len(ans)<1):
			break
		if PaperMap.has_key(ans[0]) and classPaperMap.has_key(ans[0]):
			p1=PaperMap.get(ans[0])
			PaperCiteCnt[ans[0]]=0;
			for j in range(1,len(ans)):
				if PaperMap.has_key(ans[j]) and classPaperMap.has_key(ans[j]):
					p2=PaperMap.get(ans[j])
					if not p1 == p2:
						PaperCiteCnt[ans[0]]+=1						
						count(matrix,p1,p2,dict,List)
	csvfile.close()
	for i in range(1,num):
		t =  sum([item for item in matrix[i]])

		if t > 0:
			for j in range(0,num):
				matrix[i][j]=float(matrix[i][j])/t
				'''
	for i in range(1,5):
		print i
		print List[i]
		print pj.get(List[i])
		print IF.get(pj.get(List[i]) )
		'''
	return (matrix,dict,List,PaperCiteCnt)
#i j or j i??????
def count(matrix,p1,p2,dict,List):
	matrix[ dict[p2]] [ dict[p1] ] += 1
		#	print List[dict[id1]]+' '+List[dict[id2]]

def setMatrix(num):
	return [ [0]*num for i in range(num)]
