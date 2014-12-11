import sys
import csv
import numpy as np
csv.field_size_limit(sys.maxsize)
def run(Author,Paper,Relation,classFile,agencyFile,IF_file,paper2journal):
	AuthorMap=getAuthorMap(Author)
	print len(AuthorMap)
	AllPaperMap=getAllPaperMap(Paper)
	print len(AllPaperMap)
	Relation=getRelation(Relation)
	print len(Relation)
	classPaperMap=getClassPaperMap(AllPaperMap,classFile)
	print len(classPaperMap)
	agency=getAgencyMap(agencyFile)
	IF=getIF(IF_file)
	pj=getpaper2journal(paper2journal)
	return (AuthorMap,AllPaperMap,Relation,classPaperMap,agency,IF,pj)

def getIF(filename):
	X={};
	csvfile = file(filename, 'rb')
	reader = csv.reader(csvfile)
	for line in reader:
		string=''.join(line)
		ans=string.split('\t')
		if(len(ans)<2):
			break
		s=ans[0].upper()
#		s=ans[0]
		X[s]=float(ans[1])
		print s+" "+str(X[s])
	csvfile.close()

	return X

def getAuthorMap(filename):
	csvfile = file(filename, 'rb')
	reader = csv.reader(csvfile)
	X={};
	for line in reader:
		str=''.join(line)
		ans=str.split('\t')
		if(len(ans)<2):
			break
		X[ans[1]]=ans[0]
	csvfile.close()
	return X

def getAgencyMap(filename):
	csvfile = file(filename, 'rb')
	reader = csv.reader(csvfile)
	X={};
	for line in reader:
		str=''.join(line)
		ans=str.split('\t')
		if(len(ans)<2):
			break
		X[ans[0]]=ans[1]
	csvfile.close()
	return X

def getpaper2journal(filename):
	file_object = open(filename)
	X={};
	for line in file_object:
		str=''.join(line)
		ans=str.split('\t')
		if(len(ans)<2):
			break
		X[ans[0]]=ans[1]
	file_object.close()
	return X

def getAllPaperMap(filename):
	csvfile = file(filename, 'rb')
	reader = csv.reader(csvfile)
	X={};
	for line in reader:
		str=''.join(line)
		ans=str.split('\t')
		if(len(ans)<2):
			break
		X[ans[1]]=ans[0]

	csvfile.close()
	return X

def getClassPaperMap(PaperMap,filename):
	csvfile = file(filename, 'rb')
	reader = csv.reader(csvfile)
	X={};
	for line in reader:
		str=''.join(line)
		ans=str.split('\t')
		for j in range(0,len(ans)):
			if PaperMap.has_key(ans[j]):
				if not X.has_key(ans[j]):
					X[ans[j]]=1
	csvfile.close()
	return X



def getRelation(filename):
	csvfile = file(filename, 'rb')
	reader = csv.reader(csvfile)
	dict={};
	List=[];
	List.append([])
	#List index begin from 0,so add one 
	for line in reader:
		str=''.join(line)
		
		ans=str.split('\t')
		if(len(ans)<2):
			break
		if not dict.has_key(ans[0]):
			dict[ans[0]]=ans[0]
			List.append([])
			
		id=dict.get(ans[0])

		List[int(id)].append(ans[1])

	csvfile.close()
	return List