# Continuous Delivery Guide

## Running Jenkins Locally for Pipeline Validation

```bash
 java -jar jenkins.war --httpPort=9090
```


## Automating Jenkins Job Creation

Testing Jenkins Jobs

```bash
 jenkins-jobs  --conf ./jenkins_jobs.ini  test jobs
```

Creating or Updating Jenkins Jobs

```bash
 jenkins-jobs  --conf ./jenkins_jobs.ini  update jobs
```



