import requests
import logging
import pytest



BASE_URL = "http://localhost:8080"

@pytest.fixture(scope='module', autouse=True)
def setup_module():
    setup_logging()

def setup_logging():
    logging.basicConfig(
        level=logging.INFO,
        format='%(asctime)s - %(levelname)s - %(message)s',
    )
def log_http_response(response):
    logging.info(f"Status Code: {response.status_code}")
    logging.info(f"Response Body: {response.text}")


def test_get_all_jobs():
    response = requests.get(f"{BASE_URL}/jobs")
    log_http_response(response)
    assert response.status_code == 200


def test_create_job():
    new_job = {"jobName": "New Job", "jobType": "build", "status": "pending"}
    response = requests.post(f"{BASE_URL}/jobs", json=new_job)
    log_http_response(response)
    assert response.status_code == 201


def test_get_job_by_id():
    job_id = 1
    response = requests.get(f"{BASE_URL}/jobs/{job_id}")
    log_http_response(response)
    assert response.status_code == 200


def test_update_job_status():
    job_id = 1
    new_status = {"status": "completed"}
    response = requests.put(f"{BASE_URL}/jobs/{job_id}", json=new_status)
    log_http_response(response)
    assert response.status_code == 200


def test_delete_job():
    job_id = 1
    response = requests.delete(f"{BASE_URL}/jobs/{job_id}")
    log_http_response(response)
    assert response.status_code == 204
