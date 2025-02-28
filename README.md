# CoinProject

This project has been developed to process, analyse and visualise data collected from cryptocurrency markets in real-time or batch with big data technologies such as Scala, Spark, Flink and Kafka.

**Purpose**
	
- Data Ingestion:
Pulling trade data from a specific exchange or API.
- Data Processing:
Using Spark/Flink to transform and clean the data and perform basic analyses (trading volume, average price, etc.).
- Data Storage:
Saving data to a database or Data Lake environment after analysis.
- Data Visualisation:
To present the obtained metrics and trends in a user-friendly way through a dashboard or reporting tool.

## Project Structure

The folders and files in this repository generally serve the following purposes:
- docker/: Docker configuration files of the services used in the project (Kafka, Spark, etc.).
- kafka/, spark/, flink/: Setting files or sample job codes of related technologies (Spark/Flink jobs written in Scala, etc.).
- scripts/: Scripts that automate operations such as data collection or starting/ending a workflow.
- src/: Scala source code; contains the main flow of data processing, analysis and models.
- conf/: Project configuration files (e.g. application settings, connection information, etc.).

File and folder names may vary depending on the features you develop in the project or the tools you use.

Installation and Use

1. Clone the Warehouse:
```
git clone https://github.com/bayramkiziltan/CoinProject.git
cd CoinProject
```
2. Launch Docker Environment (Optional):
If you plan to run Kafka, Spark or database containers through Docker, after reviewing and editing the relevant docker-compose.yml file:
```
docker-compose up -d
```

3. Run the Data Collection Script:
- If there is a script (for example, data_ingestion.sh) under /scripts/ that manages data collection or data extraction from the API, you can set the necessary parameters and run it.
4. Start Spark / Flink Jobs:
- Run your Spark or Flink jobs for real-time or batch data processing.
- Example:
```
spark-submit --class com.example.MainSparkJob spark/spark-job.jar
```

5. Analysing and Visualising Data:
- You can analyse the processed data with tools such as SQL, Grafana, Tableau or develop your own dashboard.

## How does it work?
1. Data Ingestion:
- By connecting to the API of a specific crypto exchange (Binance), transaction data is extracted at regular intervals or in real time.
2. Data Processing:
- Spark or Flink carries out the analysis process by filtering, transforming and calculating different metrics (average price, maximum/minimum values, trading volumes, etc.).
3. Data Storage:
- The results obtained are saved in a relational database or NoSQL / Data Lake environment.
4. Reporting / Visualisation:
- Finally, data visualisation tools are used to create real-time or historical trend analyses, summary tables and charts.
