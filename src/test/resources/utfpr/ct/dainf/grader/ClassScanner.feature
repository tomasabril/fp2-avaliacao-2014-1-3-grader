Feature: Scans application for packages and classes
    As a CS teacher
    I want to test students' programming assignments
    In order to find out if classes have been correctly created

    Background:
        Given the maximum grade is 2
        Given the main class is 'Avaliacao3'
        Given I set the script timeout to 3000
        Given I evaluate 'import utfpr.ct.dainf.grader.*;'
        Given I evaluate 'String testFilePath = ClassLoader.getSystemClassLoader().getResource("testdata.txt").getFile();' 
        And I evaluate 'java.io.File testFile = new java.io.File(testFilePath);'
        Given I evaluate 'String inputFilePath = ClassLoader.getSystemClassLoader().getResource("input.txt").getFile();' 
        And I evaluate 'java.io.File inputFile = new java.io.File(inputFilePath);'
    
    Scenario: Make class Credor comparable (0.2)
        Given I report 'STARTING GRADING...'
        Given I report 'GRADING TASK 1...'
        Given class 'Credor' exists somewhere store class in <credorClass>
        And I import <credorClass>
        And class <credorClass> implements 'java.lang.Comparable'
        Then award .05 points
        Given class <credorClass> implements 'java.lang.Comparable<utfpr.ct.dainf.if62c.avaliacao.Credor>'
        Then award .06 points
        Given I evaluate 'credorTest1 = new Credor(1L, "Fulano", 1111.11, new java.util.Date(111111))'
        Given I evaluate 'credorTest2 = new Credor(2L, "Beltrano", 2222.22, new java.util.Date(222222));'
        And expression 'credorTest1.compareTo(credorTest2) < 0' evaluates to <true>
        Then award .03 points
        Given expression 'credorTest2.compareTo(credorTest1) > 0' evaluates to <true>
        Then award .03 points
        Given expression 'credorTest1.compareTo(credorTest1) == 0' evaluates to <true>
        Then award .03 points


    Scenario: Turn class CredorComparator into an comparator (0.2)
        Given I report 'GRADING TASK 2...'
        Given class 'CredorComparator' exists somewhere store class in <credorComparatorClass>
        And class <credorComparatorClass> implements 'java.util.Comparator'
        And I import <credorComparatorClass>
        Then award .05 points
        Given class <credorComparatorClass> implements 'java.util.Comparator<utfpr.ct.dainf.if62c.avaliacao.Credor>'
        Then award .06 points
        Given I evaluate 'credorTest1 = new Credor(1L, "Fulano", 1111.11, new java.util.Date(111111))'
        Given I evaluate 'credorTest2 = new Credor(2L, "Beltrano", 2222.22, new java.util.Date(222222));'
        Given I evaluate 'credorComparator = new CredorComparator();'
        And expression 'credorComparator.compare(credorTest1, credorTest2) < 0' evaluates to <true>
        Then award .03 points
        Given expression 'credorComparator.compare(credorTest2, credorTest1) > 0' evaluates to <true>
        Then award .03 points
        Given expression 'credorComparator.compare(credorTest1, credorTest1) == 0' evaluates to <true>
        Then award .03 points

    Scenario: Implement constructor ProcessaPagamento(File) (0.1)
        Given I report 'GRADING TASK 3...'
        Given class 'ProcessaPagamento' exists somewhere store class in <processaPagamentoClass>
        And I import <processaPagamentoClass>
        And class <processaPagamentoClass> declares field 'reader' save in <readerField>
        And field <readerField> is of type 'java.io.BufferedReader'
        And I evaluate 'ProcessaPagamento pp1Test = new ProcessaPagamento(testFile)'
        Then award .05 points
        Given I evaluate 'java.io.File dummyFile = new File("/dummy/file.txt")'
        And evaluating 'new ProcessaPagamento(dummyFile)' throws instance of 'java.io.FileNotFoundException' save in <dummyEx>
        Then award .05 points

    Scenario: Implement constructor ProcessaPagamento(String) (0.1)
        Given I report 'GRADING TASK 4...'
        Given class 'ProcessaPagamento' exists somewhere store class in <processaPagamentoClass>
        And I import <processaPagamentoClass>
        And class <processaPagamentoClass> declares field 'reader' save in <readerField>
        And field <readerField> is of type 'java.io.BufferedReader'
        And I evaluate 'ProcessaPagamento pp1Test = new ProcessaPagamento(testFilePath)'
        Then award .05 points
        Given evaluating 'new ProcessaPagamento("/dummy/file.txt")' throws instance of 'java.io.FileNotFoundException' save in <dummyEx>
        Then award .05 points

    Scenario: Implement method getNextLine() (0.2)
        Given I report 'GRADING TASK 5...'
        Given class 'ProcessaPagamento' exists somewhere store class in <processaPagamentoClass>
        And I import <processaPagamentoClass>
        And class <processaPagamentoClass> declares 'getNextLine()' method save in <getNextLineMethod>
        And member <getNextLineMethod> has 'private' modifier
        And method <getNextLineMethod> returns type 'java.lang.String'
        And I evaluate 'java.io.BufferedReader testReader = new java.io.BufferedReader(new java.io.FileReader(testFile))'
        And I evaluate 'String testLine = testReader.readLine();'
        And I evaluate 'ProcessaPagamento pp2Test = new ProcessaPagamento(testFile)'
        And I set <getNextLineMethod> accessible
        And expression 'getNextLineMethod.invoke(pp2Test, null)' evaluates to <testLine>
        Then award .1 points
        And I evaluate 'testLine = testReader.readLine(); testReader.close()'
        And expression 'getNextLineMethod.invoke(pp2Test, null)' evaluates to <testLine>
        Then award .1 points

    Scenario: Implement method processaLinha(String) (0.2)
        Given I report 'GRADING TASK 6...'
        Given class 'ProcessaPagamento' exists somewhere store class in <processaPagamentoClass>
        And I import <processaPagamentoClass>
        And class <processaPagamentoClass> declares 'processaLinha(java.lang.String)' method save in <processaLinhaMethod>
        And member <processaLinhaMethod> has 'private' modifier
        And method <processaLinhaMethod> returns type 'utfpr.ct.dainf.if62c.avaliacao.Credor'
        And I set <processaLinhaMethod> accessible
        And I evaluate 'ProcessaPagamentoTest procPgtoTest = new ProcessaPagamentoTest(testFile)'
        And I evaluate 'testLine = procPgtoTest.getNextLine()'
        And I evaluate 'CredorTest credorTest = procPgtoTest.processaLinha(testLine)'
        And I evaluate 'ProcessaPagamento pp3Test = new ProcessaPagamento(testFile)'
        And I evaluate 'utfpr.ct.dainf.if62c.avaliacao.Credor credor = processaLinhaMethod.invoke(pp3Test, new Object[] { testLine })'
        Then award .04 points

    Scenario: Check cpf from processaLinha(String) call
        Given expression 'credor != null' evaluates to <true>
        Given expression 'credor.getCpf()' evaluates to <credorTest.getCpf()>
        Then award .04 points

    Scenario: Check nome from processaLinha(String) call
        Given expression 'credor != null' evaluates to <true>
        Given expression 'credor.getNome()' evaluates to <credorTest.getNome()>
        Then award .02 points

    Scenario: Check valor from processaLinha(String) call
        Given expression 'credor != null' evaluates to <true>
        Given expression 'credor.getValor()' evaluates to <credorTest.getValor()>
        Then award .04 points

    Scenario: Check data from processaLinha(String) call
        Given expression 'credor != null' evaluates to <true>
        Given expression 'credor.getData()' evaluates to <credorTest.getData()>
        Then award .05 points

    Scenario: Check processaLinha(String) call for exceptions
        Given expression 'credor != null' evaluates to <true>
        Given evaluating 'processaLinhaMethod.invoke(pp3Test, new Object[] { "abc123" })' throws instance of 'java.lang.Throwable' save in <dummyEx>
        Then award .01 points

    Scenario: Implement method getNextCredor() (0.2)
        Given I report 'GRADING TASK 7...'
        Given class 'ProcessaPagamento' exists somewhere store class in <processaPagamentoClass>
        And I import <processaPagamentoClass>
        And class <processaPagamentoClass> declares 'getNextCredor()' method save in <getNextCredorMethod>
        And member <getNextCredorMethod> has 'private' modifier
        And method <getNextCredorMethod> returns type 'utfpr.ct.dainf.if62c.avaliacao.Credor'
        And I set <getNextCredorMethod> accessible
        And I evaluate 'ProcessaPagamentoTest ppTestData = new ProcessaPagamentoTest(testFile)'
        And I evaluate 'java.util.List credorListTest = ppTestData.getUnorderedCredorList()'
        And I evaluate 'ProcessaPagamento pp4Test = new ProcessaPagamento(testFile)'
        And I evaluate 'utfpr.ct.dainf.if62c.avaliacao.Credor credorData = getNextCredorMethod.invoke(pp4Test, null)'
        Then award .05 points
        Given expression 'credorListTest.get(0).equalsLenient(credorData)' evaluates to <true>
        Then award .05 points
        Given I evaluate 'credorData = getNextCredorMethod.invoke(pp4Test, null)'
        And expression 'credorListTest.get(1).equalsLenient(credorData)' evaluates to <true>
        And I evaluate 'credorData = getNextCredorMethod.invoke(pp4Test, null)'
        And expression 'credorListTest.get(2).equalsLenient(credorData)' evaluates to <true>
        And I evaluate 'credorData = getNextCredorMethod.invoke(pp4Test, null)'
        Then award .05 points
        Given expression 'credorData == null' evaluates to <true>
        Then award .05 points


    Scenario: Implement method getCredorList() (0.2)
        Given I report 'GRADING TASK 8...'
        Given class 'ProcessaPagamento' exists somewhere store class in <processaPagamentoClass>
        And I import <processaPagamentoClass>
        And class <processaPagamentoClass> declares 'getCredorList()' method save in <getCredorListMethod>
        And member <getCredorListMethod> has 'public' modifier
        And method <getCredorListMethod> returns type 'java.util.List'
        And I evaluate 'ProcessaPagamentoTest ppTestData = new ProcessaPagamentoTest(testFile)'
        And I evaluate 'java.util.List credorListTest = ppTestData.getOrderedCredorList()'
        And I evaluate 'ProcessaPagamento pp5Test = new ProcessaPagamento(testFile)'
        And I evaluate 'testList = pp5Test.getCredorList()'
        Then award .05 points
        Given expression 'ProcessaPagamentoTest.isSameCredorList(credorListTest, testList)' evaluates to <true>
        Then award .1 points
        Given I get field 'reader' value in super class of <pp5Test> save in <dummyTestReader>
        Given evaluating 'dummyTestReader.readLine()' throws instance of 'java.io.IOException' save in <dummyEx>
        Then award .05 points

    Scenario: Implement method getCredorMap() (0.2)
        Given I report 'GRADING TASK 9...'
        Given class 'ProcessaPagamento' exists somewhere store class in <processaPagamentoClass>
        And I import <processaPagamentoClass>
        And class <processaPagamentoClass> declares 'getCredorMap()' method save in <getCredorMapMethod>
        And member <getCredorMapMethod> has 'public' modifier
        And method <getCredorMapMethod> returns type 'java.util.Map'
        And I evaluate 'ProcessaPagamentoTest ppTestData = new ProcessaPagamentoTest(testFile)'
        And I evaluate 'java.util.List credorListTest = ppTestData.getOrderedCredorList()'
        And I evaluate 'ProcessaPagamento pp6Test = new ProcessaPagamento(testFile)'
        And I evaluate 'testMap = pp6Test.getCredorMap()'
        Then award .05 points
        Given expression 'ProcessaPagamentoTest.isSameCredorMap(credorListTest, testMap)' evaluates to <true>
        Then award .1 points
        Given I get field 'reader' value in super class of <pp6Test> save in <dummyTestReader>
        Given evaluating 'dummyTestReader.readLine()' throws instance of 'java.io.IOException' save in <dummyEx>
        Then award .05 points

    Scenario: Run program and check for correct output. (0.2)
        Given I report 'GRADING TASK 10...'
        Given class 'Avaliacao3' exists somewhere store class in <clazz>
        And I import <clazz>
        Given I set output to <mainout>
        Given I set input from file <inputFile>
        And I evaluate 'Avaliacao3.main(new String[0])'
        And I set output to <default>
        Given I set input from file <default>
        And I evaluate 'outstr = mainout.toString()'
        Given I report <"OUTPUT: \n" + outstr>
        And expression 'outstr.trim().isEmpty()' evaluates to <false>
        Then award .05 points
        And expression 'outstr.contains(credorListTest.get(0).toString())' evaluates to <true>
        And expression 'outstr.contains(credorListTest.get(1).toString())' evaluates to <true>
        And expression 'outstr.contains(credorListTest.get(2).toString())' evaluates to <true>
        Then award .15 points

    Scenario: Run program and check for input processing. (0.2)
        Given I report 'GRADING TASK 11...'
        Given expression 'outstr.contains(credorListTest.get(1).toString())' evaluates to <true>
        And expression 'outstr.contains(credorListTest.get(2).toString())' evaluates to <true>
        Then award .06 points

    Scenario: Check for non-existent record treatment.
        Given expression 'outstr.toLowerCase().contains("inexistente")' evaluates to <true>
        Then award .07 points

    Scenario: Check for non-numeric input treatment.
        Given expression 'outstr.toLowerCase().contains("informe")' evaluates to <true>
        Then award .07 points

    Scenario: Report final grade.
        Given I report grade formatted as 'FINAL GRADE: %.1f'
