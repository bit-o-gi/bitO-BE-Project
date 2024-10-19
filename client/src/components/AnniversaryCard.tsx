import {Card, CardBody, Typography, Avatar} from "@material-tailwind/react";

const customers = [
    {
        title: "200일",
        when: "2024-10-05",
        dday: "D-98",
        image:
            "https://demos.creative-tim.com/test/corporate-ui-dashboard/assets/img/team-1.jpg",
    },
    {
        title: "300일",
        when: "2024-11-05",
        dday: "D-108",
        image:
            "https://demos.creative-tim.com/test/corporate-ui-dashboard/assets/img/team-6.jpg",
    },
    {
        title: "1주년",
        when: "2024-12-03",
        dday: "D-210",
        image:
            "https://demos.creative-tim.com/test/corporate-ui-dashboard/assets/img/team-2.jpg",
    },
    {
        title: "생일",
        when: "2024-12-12",
        dday: "D-320",
        image:
            "https://demos.creative-tim.com/test/corporate-ui-dashboard/assets/img/team-3.jpg",
    },
    {
        title: "600일",
        when: "2025-01-03",
        dday: "D-420",
        image:
            "https://demos.creative-tim.com/test/corporate-ui-dashboard/assets/img/team-4.jpg",
    },
];

export function AnniversaryCard() {
    return (
        <Card className="w-96">
            <CardBody>
                <div className="divide-y divide-gray-200">
                    {customers.map(({title, when, dday, image}, index) => (
                        <div
                            key={index}
                            className="flex items-center justify-between pb-3 pt-3 last:pb-0"
                        >
                            <div className="flex items-center gap-x-3">
                                <Avatar size="sm" src={image} alt={title}/>
                                <div>
                                    <Typography color="blue-gray" variant="h6">
                                        {title}
                                    </Typography>
                                    <Typography variant="small" color="gray">
                                        {when}
                                    </Typography>
                                </div>
                            </div>
                            <Typography color="blue-gray" variant="h6">
                                {dday}
                            </Typography>
                        </div>
                    ))}
                </div>
            </CardBody>
        </Card>
    );
}