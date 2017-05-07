package de.finreach;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.finreach.business.model.InputDTO;
import de.finreach.business.util.GoodsExpressionParser;
import de.finreach.business.util.InputFileReader;
import de.finreach.business.util.InputParser;
import de.finreach.business.util.QuestionParser;

@SpringBootApplication
public class FinreachTestApplication implements CommandLineRunner
{
    @Autowired
    private InputFileReader inputFileReader;

    @Autowired
    private InputParser inputParser;

    @Autowired
    private GoodsExpressionParser goodsExpressionParser;

    @Autowired
    private QuestionParser questionParser;
    
    @Value("${input.filename:null}")
    private String inputFileName;

    public static void main(String[] args)
    {
        SpringApplication.run(FinreachTestApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception
    {

        // 1.read input from the file as sentences
        List<String> readLines = inputFileReader.readInputFromFile(inputFileName);

        // 2.call service to create InputDTO
        InputDTO inputDTO = inputParser.parse(readLines);

        // 3.create the goodsList
        inputDTO = goodsExpressionParser.parse(inputDTO);

        // 4.parse the question and generate the output
        List<String> outputs = questionParser.parse(inputDTO);

        // 5.display the output
        displayOutput(outputs);
    }

    public void displayOutput(List<String> outputs)
    {
        System.out.println("------------------Output-------------------");
        for (String output : outputs)
            System.out.println(output);
    }
}
